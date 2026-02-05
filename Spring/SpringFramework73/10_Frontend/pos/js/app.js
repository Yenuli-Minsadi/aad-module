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