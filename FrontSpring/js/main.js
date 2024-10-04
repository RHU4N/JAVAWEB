import { check, signout } from "./auth.js";

const base_url = 'http://localhost:8080';
let categories;

document.onreadystatechange = () => {
    setInterval(check, 10000);
    $("#sout-b").click(signout)
    fillCategories();
    setTimeout(categoriesGetAll,2000)
}

const categoriesGetAll = () =>{
    // const categoriesList = fillCategories();
    const ul = $("<ul>");
    console.table(categories)
    categories.forEach(e => {
        const li = $("<li>");
        const lbl = $(`<label>${e.category}</label>`)
        ul.append(li.append(lbl));
    });
    $("#categories").append(ul);
}

const fillCategories = () =>{
    $.ajax({
        type: 'GET',
        url: `${base_url}/category`,
        contentType: 'application/json',
        dataType: 'json',
        success: (res) => { categories = res}
    });
    
}
