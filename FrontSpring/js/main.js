import { check, signout } from "./auth.js";

const base_url = 'http://localhost:8080';
let activeCategory;

document.onreadystatechange = () => {
    setInterval(check, 10000);
    $("#sout-b").click(signout)
    $("#add-category").click(addCategory)
    $("#add-task").click(addTask)
    loadCategories();
    //setTimeout(categoriesGetAll,2000)
}

const renderCategories = (categories) => {
   $("#categories>ul").empty();
    const ul = $("<ul>");
    console.table(categories);
    categories.forEach(e => {
        const li = $("<br><li>");
        const lbl = $(`<span style="background-color:${e.color}">${e.category}</span>`);
        lbl.click(() => loadTasks(e));

        ul.append(li.append(lbl));
    });
    $("#categories").append(ul);
}

const loadCategories = () => {
    $.ajax({
        type: 'GET',
        url: `${base_url}/category`,
        contentType: 'application/json',
        dataType: 'json',
        success: (res) => { renderCategories(res); }
    });
}

const loadTasks = (category) => {
    $.ajax({
        type: 'GET',
        url: `${base_url}/task/category/${category.id}`,
        headers: {"token": localStorage.getItem("token")},
        contentType: 'application/json',
        dataType: 'json',
        success: (res) => { renderTasks(category, res); }
    });
}

const renderTasks = (category, tasks) => {
    $("#task-category").text(category.category);
    activeCategory = category;

    const ul = $("<ul>");
    console.table(tasks);
    tasks.forEach(e => {
        const li = $("<li>");
        const lbl = $(`<span  class="${e.status == 'DONE'?'task-done':'task-pending'}">${e.description}</span>`);
        lbl.click(() => toggleTask(e));

        ul.append(li.append(lbl));
    });
    $("#tasks>ul").empty();
    $("#tasks").append(ul);

}

const addCategory = () =>{
    const category = $("#new-category").val();
    const color = $("#catColor").val();
    const body = `{
            "category":"${category}",
            "color":"${color}"
    }`

    $.ajax({
        type: 'POST',
        url: `${base_url}/category`,
        contentType: 'application/json',
        dataType: 'json',
        data: body,
        success: (res) => { loadCategories(res); }
    });
    cleanCategory();
}

const addTask = () => {
    const description = $("#new-task").val();
    const pri = $("#taskPri").val();
    const body = `{
        "description": "${description}",
        "priority": "${pri}",
        "category": {
                "id":"${activeCategory.id}"
            }
        }
    }`

    $.ajax({
        type: 'POST',
        url: `${base_url}/task`,
        headers: {"token": localStorage.getItem("token")},
        contentType: 'application/json',
        dataType: 'json',
        data: body,
        success: (res) => { loadTasks(activeCategory); }
    });

    cleanTask();
}

const toggleTask = (task) => {
    task.status = task.status == 'DONE'?'PENDING':'DONE'

    $.ajax({
        type: 'PUT',
        url: `${base_url}/task/${task.id}`,
        headers: {"token": localStorage.getItem("token")},
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(task),
        success: (res) => { loadTasks(activeCategory); }
    });
}

const cleanTask = () => {
    $("#new-task").val("");
    $("#taskPri").val("LOW");
}

const cleanCategory = () => {
    $("#new-category").val("");
    $("#catColor").val("#000000");
}