function saveCustomer() {
    let cId=$('#Cid').val();
    let cName=$('#Ncame').val();
    let cAddress=$('#Caddress').val();

    console.log(cId,cName,cAddress)

    $.ajax({
        url:"http://localhost:8080/api/v1/customer",
        method:"POST",
        contentType:"application/json",
        "data": JSON.stringify({
            "cid": cId,
            "name": cName,
            "address": cAddress
        }),
        success:function (res) {
            alert("done")
        },
        error:function (error) {
            alert("network error")
        }
    })
}

function updateCustomer() {
    let cId=$('#Cid').val();
    let cName=$('#Ncame').val();
    let cAddress=$('#Caddress').val();

    console.log(cId,cName,cAddress)

    $.ajax({
        url:"http://localhost:8080/api/v1/customer",
        method:"PUT",
        contentType:"application/json",
        "data": JSON.stringify({
            "cid": cId,
            "name": cName,
            "address": cAddress
        }),
        success:function (res) {
            alert("done")
        },
        error:function (error) {
            alert("network error")
        }
    })
}

function deleteCustomer() {
    let cId=$('#Cid').val();

    console.log(cId)

    $.ajax({
        url:"http://localhost:8080/api/v1/customer",
        method:"DELETE",
        contentType:"application/json",
        "data": JSON.stringify({
            "cid": cId
        }),
        success:function (res) {
            alert("done")
        },
        error:function (error) {
            alert("network error")
        }
    })
}

function getAllCustomer() {
    $.ajax({
        url: "http://localhost:8080/api/v1/customer",
        method: "GET",
        success: function (res) {
            console.log(res);
            alert("Fetched successfully");
        },
        error: function () {
            alert("network error");
        }
    });
}

function saveItem() {
    let itemId=$('#itemId').val();
    let itemName=$('#itemName').val();
    let itemQuantity=$('#itemQuantity').val();

    console.log(itemId,itemName,itemQuantity)

    $.ajax({
        url:"http://localhost:8080/api/v1/item",
        method:"POST",
        contentType:"application/json",
        "data": JSON.stringify({
            "itemId": itemId,
            "itemName": itemName,
            "itemQuantity": itemQuantity
        }),
        success:function (res) {
            alert("done")
        },
        error:function (error) {
            alert("network error")
        }
    })
}

function updateItem() {
    let itemId=$('#itemId').val();
    let itemName=$('#itemName').val();
    let itemQuantity=$('#itemQuantity').val();

    console.log(itemId,itemName,itemQuantity)

    $.ajax({
        url:"http://localhost:8080/api/v1/item",
        method:"PUT",
        contentType:"application/json",
        "data": JSON.stringify({
            "itemId": itemId,
            "itemName": itemName,
            "itemQuantity": itemQuantity
        }),
        success:function (res) {
            alert("done")
        },
        error:function (error) {
            alert("network error")
        }
    })
}

function deleteItem() {
    let itemId=$('#itemId').val();

    console.log(itemId)

    $.ajax({
        url:"http://localhost:8080/api/v1/item",
        method:"DELETE",
        contentType:"application/json",
        "data": JSON.stringify({
            "itemId": itemId
        }),
        success:function (res) {
            alert("done")
        },
        error:function (error) {
            alert("network error")
        }
    })
}

function getAllItems() {
    $.ajax({
        url: "http://localhost:8080/api/v1/item",
        method: "GET",
        success: function (res) {
            console.log(res);
            alert("Fetched successfully");
        },
        error: function () {
            alert("network error");
        }
    });
}


