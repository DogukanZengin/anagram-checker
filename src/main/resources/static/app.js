function checkAnagram() {
    $.post({
        url: "http://localhost:8080/api/anagrams",
        data: JSON.stringify({'first': $("#first").val(), 'second': $("#second").val()}),
        contentType: 'application/json'
    }).done(function(data) {
        appendResult(data);
    }).fail(function(xhr){
        $("#result").append("<tr><td style='color:red'>" + xhr.responseJSON.message + "</td></tr>");
    });
}
function appendResult(message) {
    const style = message ? "style=color:green;" : "style=color:red";
    $("#result").append("<tr><td " + style + ">" + message + "</td></tr>");
}

function clearResults() {
    $("#result tr").remove();
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#check-anagram" ).click(() => checkAnagram());
    $( "#clear-results" ).click(() => clearResults());
});