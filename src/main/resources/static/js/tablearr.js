var arrHead = new Array();

arrHead = [
    ['Model', 'Year of release', 'Price($)', 'Power reserve(km)', 'Speed(km/h)', '', '', ''],
    ['Tesla Roadster', '2008', '200000', '1000', '402'],
    ['Tesla Model S', '2012', '41799', '426', '250'],
    ['Tesla Model X', '2015', '72144', '411', '250'],
    ['Tesla Model 3', '2016', '40399', '498', '225'],
    ['Tesla Model Y', '2020', '49990', '450', '215'],
    ['Tesla Semi', '2019', '180000', '800', '200'],
    ['Tesla Cybertruck', '2021', '39900', '400', '177']
];

var model;
var year;
var cost;
var reserve;
var speed;
var button_delete;
var button_edit;
var button_save_edit;

//функция создания таблицы
function createTable() {
    var empTable = document.createElement('table');
    empTable.setAttribute('id', 'empTable'); //table id

    for (var i = 0; i < arrHead.length; i++) {
        switch(i) {
            case 0: 
                var tr1 = empTable.insertRow(0);

                for (var j = 0; j < arrHead.length; j++) {
                    var th = document.createElement('th');
                    th.innerHTML = arrHead[0][j];
                    tr1.appendChild(th);
                }
                break;

            case 1:
                var tr2 = empTable.insertRow(1);
                for (var j = 0; j < 5; j++) {
                    var td1 = document.createElement('td');
                    td1.innerHTML = arrHead[1][j];
                    tr2.appendChild(td1);
                }
                break;
            
            case 2: 
                var tr3 = empTable.insertRow(2);
                for (var j = 0; j < 5; j++) {
                    var td2 = document.createElement('td');
                    td2.innerHTML = arrHead[2][j];
                    tr3.appendChild(td2);
                }
                break;
            
            case 3: 
                var tr4 = empTable.insertRow(3);
                for (var j = 0; j < 5; j++) {
                    var td3 = document.createElement('td');
                    td3.innerHTML = arrHead[3][j];
                    tr4.appendChild(td3);
                }
                break;

            case 4: 
                var tr5 = empTable.insertRow(4);
                for (var j = 0; j < 5; j++) {
                    var td4 = document.createElement('td');
                    td4.innerHTML = arrHead[4][j];
                    tr5.appendChild(td4);
                }
                break;

            case 5: 
                var tr6 = empTable.insertRow(5);
                for (var j = 0; j < 5; j++) {
                    var td5 = document.createElement('td');
                    td5.innerHTML = arrHead[5][j];
                    tr6.appendChild(td5);
                }
                break;

            case 6: 
                var tr7 = empTable.insertRow(6);
                for (var j = 0; j < 5; j++) {
                    var td6 = document.createElement('td');
                    td6.innerHTML = arrHead[6][j];
                    tr7.appendChild(td6);
                }
                break;

            case 7: 
                var tr8 = empTable.insertRow(7);
                for (var j = 0; j < 5; j++) {
                    var td7 = document.createElement('td');
                    td7.innerHTML = arrHead[7][j];
                    tr8.appendChild(td7);
                }
                break;
        }
    }

    var div = document.getElementById('cont');
    div.appendChild(empTable);
}

function getValue(id) {
    text = document.getElementById(id).value;
    alert(text);
    return text;
}

function addRow() {
    var empTab = document.getElementById('empTable');

    var rowCnt = empTab.rows.length; // table row count.
    var tr = empTab.insertRow(rowCnt); // the table row.

    for (var i = 0; i < arrHead.length; i++) {
        var td = document.getElementById('td');
        td = tr.insertCell(i);
        
        switch(i) {
            case 0:
                model = document.getElementById('model').value;

                var td1 = document.createElement('td');
                td1.setAttribute('name','model1');
                td1.innerHTML = model;

                td.appendChild(td1);
                break;

            case 1:
                year = document.getElementById('year').value;

                var td2 = document.createElement('td');
                td2.setAttribute('name','year1');
                td2.innerHTML = year;

                td.appendChild(td2);
                break;

            case 2:
                cost = document.getElementById('cost').value;

                var td3 = document.createElement('td');
                td3.setAttribute('name','cost1');
                td3.innerHTML = cost;

                td.appendChild(td3);
                break;

            case 3:
                reserve = document.getElementById('reserve').value;

                var td4 = document.createElement('td');
                td4.setAttribute('name','reserve1');
                td4.innerHTML = reserve;

                td.appendChild(td4);
                break;

            case 4:
                year = document.getElementById('speed').value;

                var td5 = document.createElement('td');
                td5.setAttribute('name','speed1');
                td5.innerHTML = year;

                td.appendChild(td5);
                break;

            case 5:
                button_delete = document.createElement('input');
                button_delete.setAttribute('type', 'button');
                button_delete.setAttribute('value', 'Delete');
                button_delete.setAttribute('onclick', 'removeRow(this)');

                td.appendChild(button_delete);
                break;

            case 6:
                button_edit = document.createElement('input');
                button_edit.setAttribute('type', 'button');
                button_edit.setAttribute('value', 'Change');
                button_edit.setAttribute('onclick', 'editRow(this)');

                td.appendChild(button_edit);
                break;

            case 7:
                button_save_edit = document.createElement('input');
                button_save_edit.setAttribute('type', 'button');
                button_save_edit.setAttribute('value', 'Save');
                button_save_edit.setAttribute('onclick', 'saveEditRow(this)');

                td.appendChild(button_save_edit);
                break;
                
        }
    }
    document.getElementById('editingForm').reset();
}


//Удаление строки
function removeRow(oButton) {
    var empTab = document.getElementById('empTable');
    empTab.deleteRow(oButton.parentNode.parentNode.rowIndex); // button -> td -> tr.
}

function editRow(id) {
    table = document.getElementById('empTable'); //таблица найдена
    var i = id.parentNode.parentNode.rowIndex; //индекс строки взят
    var tr = document.getElementById('empTable').getElementsByTagName('TR')[i]; //сама строка
    tr.style.backgroundColor = '#47e0ff';

    document.getElementById('model').value = tr.childNodes[0].textContent;
    document.getElementById('year').value = tr.childNodes[1].textContent;
    document.getElementById('cost').value = tr.childNodes[2].textContent;
    document.getElementById('reserve').value = tr.childNodes[3].textContent;
    document.getElementById('speed').value = tr.childNodes[4].textContent;
}

function saveEditRow(id) {
    var model = document.getElementById('model').value;
    var year = document.getElementById('year').value;
    var cost = document.getElementById('cost').value;
    var reserve = document.getElementById('reserve').value;
    var speed = document.getElementById('speed').value;

     
    var tr =  id.parentNode.parentNode;

    tr.childNodes[0].textContent = model;
    tr.childNodes[1].textContent = year;
    tr.childNodes[2].textContent = cost;
    tr.childNodes[3].textContent = reserve;
    tr.childNodes[4].textContent = speed;
    
    console.log(tr);

    tr.style.backgroundColor = 'white';

    document.getElementById('editingForm').reset();
}