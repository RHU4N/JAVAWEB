import { check, signout } from "./auth.js";

const base_url = 'http://localhost:8080'

document.onreadystatechange = () => {
    setInterval(check, 10000);
    $("#sout-b").click(signout)
    loadCategories();
    //setTimeout(categoriesGetAll, 2000);
}

const renderCategories = (categories) => {
    //const categoriesList = await fillCategories();
    const ul = $("<ul>");
    console.table(categories);
    categories.forEach(e => {
        const li = $("<li>");
        const lbl = $(`<span>${e.category}</span>`);
        lbl.click(() => loadTasks(e.id));

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

const loadTasks = (categoryId) => {
    $.ajax({
        type: 'GET',
        url: `${base_url}/task/category/${categoryId}`,
        headers: {"token": localStorage.getItem("token")},
        contentType: 'application/json',
        dataType: 'json',
        success: (res) => { renderTasks(res); }
    });
}

const renderTasks = (tasks) => {
    const ul = $("<ul>");
    console.table(tasks);
    tasks.forEach(e => {
        const li = $("<li>");
        const lbl = $(`<span>${e.description}</span>`);
        lbl.click(() => toggleTask(e.id));

        ul.append(li.append(lbl));
    });
    $("#tasks").append(ul);
}