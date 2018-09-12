var input = [];

readline_object.on("line", (value) => { //Read input values
  input.push(value);
})
//Call ContestResponse when all inputs are red
readline_object.on("close", ContestResponse);


function ContestResponse() {
  //implements your code here using input array
  var nbOfPlaces = input[0];
  var nbOfTeams = input[1];
  var teams = input.map(Number);
  teams.shift();
  teams.shift();
  var levels = 0;
  for (var i = 0; i < nbOfTeams; i++) {
    for (var j = 0; j < nbOfTeams; j++) {
      if (j != i && teams[i] + teams[j] == nbOfPlaces) {
        teams[i] = 0;
        teams[j] = 0;
        levels++;
        continue;
      }
    }
  }
  console.log(levels)
}