/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    $("#filtra_obj").on('keyup', function () {
        var q = $("#filtra_obj").val();

        $.ajax({
            data: {
                userInput: q
            },
            url: "filter.json",
            dataType: 'json',
            success: function (myList) {
                $("#0risultati").remove();
                $('#tabItems').show();
                if (myList.length === 0) {
                    var noResults = document.createElement("p");
                    noResults.setAttribute("id", "0risultati");
                    var testo = document.createTextNode("La ricerca non ha prodotto risultati");
                    noResults.appendChild(testo);
                    noResults.className = "error";
                    $('#page').append(noResults);
                    $('#tabItems').hide();

                }

                $('#tabItems tr').slice(1).remove(); //mantengo gli header

                for (var i = 0; i < myList.length; i++) {
                    var currRiga = document.createElement("tr");

                    var name = myList[i].name;
                    var id = myList[i].id;
                    var url = myList[i].url;
                    var description = myList[i].description;
                    var quantity = myList[i].quantity;
                    var price = myList[i].price;

                    tdName = document.createElement("td");
                    tdName.appendChild(document.createTextNode(name));
                    currRiga.appendChild(tdName);


                    tdUrl = document.createElement("td");
                    currRiga.appendChild(tdUrl);
                    var currImg = document.createElement("img");
                    currImg.setAttribute("src", url);
                    currImg.setAttribute("alt", name);
                    tdUrl.appendChild(currImg);

                    tdDescription = document.createElement("td");
                    tdDescription.appendChild(document.createTextNode(description));
                    currRiga.appendChild(tdDescription);

                    tdQuantity = document.createElement("td");
                    tdQuantity.appendChild(document.createTextNode(quantity));
                    currRiga.appendChild(tdQuantity);

                    tdPrice = document.createElement("td");
                    tdPrice.appendChild(document.createTextNode(price));
                    currRiga.appendChild(tdPrice);


                    tdAddCart = document.createElement("td");
                    var lnkAcquisto = document.createElement("a");
                    lnkAcquisto.setAttribute("href", "./cliente.html?objId=" + id);
                    lnkAcquisto.appendChild(document.createTextNode("acquista"));
                    tdAddCart.appendChild(lnkAcquisto);
                    currRiga.appendChild(tdAddCart);

                    $('#tabItems').append(currRiga);
                }


            },
            error: function () {
                alert("An error occurred");
            }
        });
    });
});