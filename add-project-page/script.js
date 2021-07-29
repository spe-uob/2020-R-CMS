//adding and removing members on form logic
//get elements by id tag
var membersOnPage = document.getElementById("projectMembers")
var addMemberButton = document.getElementById("add_member");
var removeMemberButton = document.getElementById("remove_member");

//keep track of member count
var memberCount = 1;
//add new member to list of members on form

addMemberButton.onclick = function() {
    memberCount++;
    //create first and last name input elements
    var newFirstName = document.createElement("input");
    var newLastName = document.createElement("input");
    newFirstName.type = "text";
    newLastName.type = "text";
    newFirstName.placeholder = "First Name";
    newLastName.placeholder = "Last Name";
    newFirstName.required = true;
    newLastName.required = true;
    //set unique name and id
    newFirstName.name = "firstName" + memberCount;
    newLastName.name = "lastName" + memberCount;
    newFirstName.id = "firstName" + memberCount;
    newLastName.id = "lastName" + memberCount;
    //add to form
    membersOnPage.appendChild(newFirstName);
    membersOnPage.appendChild(newLastName);
}

//remove newest member from list of members on form
removeMemberButton.onclick = function() {
    if (memberCount > 1) {
        memberCount--;
    }
    //get all input elements underneath projectMembers field as a list
    var membersOnPageAsList = membersOnPage.getElementsByTagName('input');
    //if there is more than pair of first name and last names, remove the most recent pair
    if (membersOnPageAsList.length > 2) {
        for (var i = 0; i < 2; i++) {
            membersOnPage.removeChild(membersOnPageAsList[(membersOnPageAsList.length) - 1]);
        }
    }
}

//adding and removing extra project fields logic
addFieldButton = document.getElementById("add_field");
additionalFieldsDiv = document.getElementById("additionalFields");

//keep track of added fields
var fieldCount = 0;
//on click of Add Field button...
addFieldButton.onclick = function() {
    //open popup to get field name
    var newName = window.prompt("Enter a name for your new field: ");
    //increment additional field count
    fieldCount++;
    //create field label and input
    var newLabel = document.createElement("label");
    var newInput = document.createElement("input");
    //create and set ids for new label and input
    newInput.id = "projectAdditionalField" + fieldCount;
    newLabel.id = "projectAdditionalFieldLabel" + fieldCount;
    //set "for" attribute and inner text of label
    newLabel.htmlFor = newInput.id;
    newLabel.innerHTML = newName;
    //set "type" and "placeholder" attribute of input
    newInput.type = "text";
    newInput.placeholder = "Value for " + newName;
    //add label and input to form
    additionalFieldsDiv.appendChild(newLabel);
    additionalFieldsDiv.appendChild(newInput);
    //add Remove Field button
    //let removeButton
}

removeFieldButton = document.getElementById("remove_field");
//on click of remove_field...
removeFieldButton.onclick = function() {
    if (fieldCount > 0) {
        fieldCount--;
    }
    //remove last field label and input
    var additionalInputsAsList = additionalFieldsDiv.getElementsByTagName("input");
    var additionalLabelsAsList = additionalFieldsDiv.getElementsByTagName("label");
    additionalFieldsDiv.removeChild(additionalInputsAsList[(additionalInputsAsList.length) - 1]);
    additionalFieldsDiv.removeChild(additionalLabelsAsList[(additionalLabelsAsList.length) - 1]);
    //if last field is only additional field, remove Remove Field button
}

//adding a project to a list of JSON project objects to be added to the database
var newProjects = [];
var outputDiv = document.getElementById("outputDiv")

//get form data, convert to JSON - might not be needed

const addProject = (ev) => {
    ev.preventDefault();
    //project name, project brief, client name, client email, project members, GitHub URL
    var newProject = {
        id: Date.now(),
        projectName: document.getElementById("projectName").value,
        projectBrief: document.getElementById("projectBrief").value,
        projectClientName: document.getElementById("projectClientName").value,
        projectMembers: [],
        projectGitHubUrl: document.getElementById("projectGitHubUrl").value
    }
    //add team members
    var membersInSubmissionAsList = membersOnPage.getElementsByTagName("input");
    for (var index = 0; index < membersInSubmissionAsList.length; index++) {
        //console.log(membersInSubmissionAsList[index].value);
        if (index % 2 == 0) {
            var memberFirstName = membersInSubmissionAsList[index].value;
        } else {
            var memberLastName = membersInSubmissionAsList[index].value;
            newProject.projectMembers.push({
                firstName: memberFirstName,
                lastName: memberLastName
            });
        }
    }
    //add additional fields
    var additionalInputsAsList = additionalFieldsDiv.getElementsByTagName("input");
    var additionalLabelsAsList = additionalFieldsDiv.getElementsByTagName("label");
    for (jndex = 0; jndex < additionalInputsAsList.length; jndex++) {
        var newKey = "project" + additionalLabelsAsList[jndex].innerHTML;
        var newVal = additionalInputsAsList[jndex].value;
        newProject[newKey] = newVal;
    }
    newProjects.push(newProject);
    //reset form
    document.forms[1].reset();
    //print addition to console
    console.warn('added ', {
        newProjects
    });
    //update output
    var outputElement = document.createElement("p");
    outputElement.innerHTML = JSON.stringify(newProjects);
    outputDiv.appendChild(outputElement);
}

//wait until page is fully rendered before listening for click events on submitButton
var submitFormButton = document.getElementById('submitButton');
document.addEventListener('DOMContentLoaded', () => {
    submitFormButton.addEventListener('click', addProject);
});



