(async function(){
    async function click_delete(event){
        const response = await fetch(`/api/products/delete/${event.target.id}`,{
            method: "POST",
            headers: {"Accept": "application/json"}
        });
        if (response.ok === true) {
            window.location.href = '/';
        }
    }

    async function click_update(event){
        await fetch(`/api/products/update/${event.target.id}`, {
            method: "POST",
            headers: {"Accept": "application/json"}
        });
    }

    function append_table_list(product) {
        let products = document.getElementById("products");
        let child = document.createElement("div");
        child.innerHTML += `${product.name}`;
        child.innerHTML += `<input type="button" class="button_delete" id="${product.id}" value="x">`;
        child.innerHTML += `<input type="checkbox" class="button_update" id="${product.id}" ${product.bought ? 'checked' : ''} value="${product.bought}">`;
        products.appendChild(child);
        child.querySelector(".button_delete").addEventListener("click", click_delete);
        child.querySelector(".button_update").addEventListener("change", click_update);
    }

    async function click_redirect() {
        window.location.href = '/add';
    }

    let list = [];

    const response = await fetch("api/products/", {
        method: "GET",
        headers: {"Accept": "application/json" }
    });
    if (response.ok === true) {
        list = await response.json();

        let product;
        for (product of list) {
            append_table_list(product);
        }
        let elem = document.getElementById("button_add");
        elem.addEventListener("click", click_redirect)
    }

})();