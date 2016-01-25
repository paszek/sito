var fillDefaultDate = function(inputId) {
  var inputDate = document.getElementById(inputId);
  if(inputDate) {
    inputDate.value = moment().format('YYYY-MM-DD');
  }
}

var onLoad = function() {
  fillDefaultDate('trans-date');
}
