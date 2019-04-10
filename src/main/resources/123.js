function addTable() {
    var body = document.querySelector("body"),
        tableWidth = document.getElementById("table-width"),
        tableHeight = document.getElementById("table-height"),
        width = tableWidth.value,
        height = tableHeight.value,
        numRows = document.getElementById("rows"),
        numColumns = document.getElementById("columns"),
        rows = numRows.value,
        //columns = numColumns.value,
        columns = 4,
        tr = "",
        td = "",
        firstTable = document.querySelector("table");
    console.log(width);
    console.log(height);
    console.log(rows);
    console.log(columns);

    table = document.createElement("table"),
        checkbox = document.getElementById("checkbox");
    if (checkbox.checked == true) {
        table.setAttribute("border", "2px");
    } else {
        table.setAttribute("border", "0");
    }
    table.setAttribute("width", width);
    table.setAttribute("height", height);
    for (var i = 0; i < rows; i++) {
        tr = document.createElement("tr");
        for (var j = 0; j < columns; j++) {
            td = document.createElement("td");
            if (i==0)
            switch (j) {
                case 0:
                    text=document.createTextNode("Имя задачи");break;
                case 1:
                    text=document.createTextNode("Колличество вариантов");break;
                case 2:
                    text=document.createTextNode("Колличество баллов");break;
                case 3:
                    text=document.createTextNode("Время выполнения");break;
                default:
                    text = document.createTextNode((i) + "." + (j + 1));break;
            } else
            text = document.createTextNode((i) + "." + (j + 1));
            td.appendChild(text);
            tr.appendChild(td);
        }
        table.appendChild(tr);
    }
    //console.log(tr);
    //console.log(td);

    table.contentEditable = true;
    table.id = "mytable";

    if (firstTable == null) {
        return body.appendChild(table);
    } else {
        var newTable = body.appendChild(table);
        return document.body.replaceChild(newTable, firstTable);
    }
}

function destroyTable() {
    var body = document.querySelector("body"),
        table = document.querySelector("table"),
        checkbox = document.getElementById("checkbox")
    tableWidth = document.getElementById("table-width"),
        tableHeight = document.getElementById("table-height"),
        numRows = document.getElementById("rows"),
        numColumns = document.getElementById("columns");
    if (document.querySelector("table") != null) {
        document.body.removeChild(table);
        checkbox.checked = false;
        tableWidth.value = "";
        tableHeight.value = "";
        numRows.value = "";
        numColumns.value = "";
    }else {
        alert(":)")
    }
}

function sentdata() {
    var array = [];
  var table = document.getElementById("mytable");
  var rows = table.getElementsByTagName("tr");
  for(var i=1;i<rows.length;i++){
      var columns = rows[i].getElementsByTagName("td");
      var mass=[];
      for(var j=0;j<columns.length;j++)
          mass.push(columns[j].innerText);
      array.push(mass);
    }
  console.log(array);
  return array;
}
