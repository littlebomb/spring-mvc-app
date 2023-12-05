(async function(){
    async function click_add() {
        const response = await fetch("api/products/add", {
            method: "POST",
            headers: { "Accept": "application/json", "Content-Type": "application/json"},
            body: JSON.stringify({
            productName: document.getElementById("productName").value,
            })
        });
        if (response.ok === true) {
            click_redirect()
        }
    }
    function click_redirect() {
        window.location.href='/';
    }
    let elemAdd = document.getElementById("button_add");
    elemAdd.addEventListener("click", click_add);
    let elemBack = document.getElementById("button_back");
    elemBack.addEventListener("click", click_redirect);
})();