/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $("#inputFiltro").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#tablaFiltro tr").filter(function() {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });
});




