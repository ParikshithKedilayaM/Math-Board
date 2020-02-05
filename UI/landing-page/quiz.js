var index = 0;
window.onload = function() {
    //call API to get quiz question
    var question = "3+2-4+7*4";
    var answer = "29";
    var quizList = getQuestionsAPI();
    quizList.forEach(function(element) {
        question = element.question;
        answer = element.answer;
    });
    
    this.document.getElementById("answer").innerHTML=answer;
    this.loadAllBlocks(question);
}
function getQuestionsAPI() {
    var url = "http://127.0.0.1:8080/GetQuiz/" + sessionStorage.getItem("grade");
    var xhttp = new XMLHttpRequest();
    var response = "";
	xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
			 if (this.responseText === "") {
				 alert("Error Occurred.. Try again!");
				 return false;
			 }
            response = JSON.parse(this.responseText);
            
         }
    };
	xhttp.open("GET", url, false);
    xhttp.send();
    return response.quizzes;
}
function loadBlock(elm) {
    var parent = document.getElementById("blockContainer");
    var newBlock = document.createElement("div");
    newBlock.id = elm;
    newBlock.className = "number";
    newBlock.setAttribute("draggable","true");
    newBlock.setAttribute("ondragstart", "drag(event)");
    newBlock.appendChild(this.loadSpan(elm));
    parent.appendChild(newBlock);
}
function loadSpan(elm) {
    var spanTag = document.createElement("span");
    spanTag.className = "numberText";
    spanTag.innerHTML = elm;
    return spanTag;
}
function loadAllBlocks(question) {
    if (question!=="" && question!==null) {
        question = shuffle(question);
        for (var i = 0; i < question.length; i++) {
            loadBlock(question.charAt(i));
        }
    }
}
function shuffle(str) {
    var a = str.split(""),
        n = a.length;

    for(var i = n - 1; i > 0; i--) {
        var j = Math.floor(Math.random() * (i + 1));
        var tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    return a.join("");
}

function evaluateQuiz() {
    if (this.document.getElementById("answer").innerHTML === this.calculateResult().toString()) {
        alert("Congratulations! You have answered correctly!");
        
    } else {
        alert("Your answers are incorrect");
        return false;
    }
}
function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function allowDrop(ev) {
    ev.preventDefault();
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
	if (!data.includes("sandboxItem")) {
		var copyNbr = document.createElement("div");
		var orgNbr = document.getElementById(data);
		copyNbr.innerHTML = orgNbr.innerHTML;
		var parent = document.getElementById("sandboxExpression");
		var doc = document.createDocumentFragment();
		doc.appendChild(copyNbr);
		copyNbr.setAttribute("draggable","true");
		copyNbr.setAttribute("style","margin:0px");
		copyNbr.id = "sandboxItem" + index++ ;
		parent.appendChild(doc);

		if (orgNbr.className === "number") {
			copyNbr.setAttribute("class", "blockContainer number numberText");
		}
		else if (orgNbr.className === "operator") {
			copyNbr.setAttribute("class", "blockContainer operator numberText");
		}
		else if (orgNbr.className === "number paranthesis") {
			copyNbr.setAttribute("class", "blockContainer number paranthesis numberText");
		}
		else if (orgNbr.className === "variable") {
			copyNbr.setAttribute("class", "blockContainer variable numberText");
		}
		else if (orgNbr.className === "operatorExpression") {
			copyNbr.setAttribute("class", "blockContainer operatorExpression numberText");
		}
		else if (orgNbr.className === "trigExpression") {
			copyNbr.setAttribute("class", "blockContainer trigExpression  numberText");
        }
        orgNbr.remove();
	}
}

function calculateResult() {
    try {
        var items = document.getElementById('sandboxExpression').childNodes;
        var rs = "";
        for (var i = 0; i < items.length; i++) {
            console.log(items[i].childNodes);
            if (items[i].nodeName != "#text") {
                span = items[i].childNodes;
                rs += (span[0].innerHTML);
            }
        }
        console.log(rs);
        var result = eval(rs);
        console.log(result);
        if(isNaN(result)) result = 0;
        document.getElementById("finalResult").value = result;
        return result;
    }
    catch (err) {
        alert("Invalid Expression");
    }

}