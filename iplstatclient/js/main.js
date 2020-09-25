
const selectId = document.querySelector("#selectData");

getLabels();

function getLabels() {
    labels = [];
    fetch('http://localhost:8080/api/v1/iplstat/labels/')
    .then(response=>response.json())
    .then(res=>{
        labels = res["labels"];
        str = "<select onchange ='getPlayers()' id='idTeamLabel'>";
        str += "<option value=''>Select Team label</option>";
        for (let label of labels) {
            str += `<option value=${label}>${label}</option>`
        }
        str += "</select>";
        selectId.innerHTML = str;
    })
    
   
};

function getPlayers(){
        label = document.querySelector("#idTeamLabel").value;
        if(label.trim().length== 0)
            return ;
        else{
            players = [];
            fetch(`http://localhost:8080/api/v1/iplstat/players/${label}`)
            .then(response=>response.json())
            .then(res=>{
                players = res;
                showPlayers(players);
                getPlayerCount(label);
            })
        }

}

function getPlayerCount(label){
 
    
      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      
 
    

    

}

function drawChart(){
    fetch(`http://localhost:8080/api/v1/iplstat/rolecount/${label}`)
            .then(response=>response.json())
            .then(res=>{
        
         rows = [];
         for(let r of res){
             rows.push([r['role'],r['count']]);
         }
         var data = new google.visualization.DataTable();
        data.addColumn('string', 'Rolname');
        data.addColumn('number', 'Count');
        data.addRows(rows);

        // Set chart options
        var options = {'title':'Players count by Team',
                       'width':400,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
               
    });
}

function getPlayersByRoleAndLabel(){

}


function showPlayers(players){
   const idShowPlayers =  document.querySelector("#idShowPlayers");

   let str ="<table>";
   str += "<tr><th>Name</th><th>Role</th><th>Label</th><th>Price</th></tr>";
  
   for(let player of players){
        str +=`<tr><td>${player['name']}</td><td>${player['role']}</td><td>${player['label']}</td>
        <td>${player['price']}</td></tr>`;
    }
    str +="</table>";
    idShowPlayers.innerHTML = str;
}