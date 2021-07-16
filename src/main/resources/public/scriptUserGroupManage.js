const URL = 'http://localhost:8081';
let userGroups = [];
let mode = 'create';
let currentUserGroup;



// API Requests
const createUserGroup= (userGroup) => {
    fetch(`${URL}/usergroup`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("auth")
        },
        body: JSON.stringify(userGroup)
    }).then((result) => {
        result.json().then((userGroup) => {
            userGroups.push(userGroup);
            renderUserGroup();
        });
    });
};

const indexUserGroup = () => {
    fetch(`${URL}/usergroup`, {
        method: 'GET',
        headers:{
            'Authorization': sessionStorage.getItem("auth")
        }
    }).then((result) => {
        result.json().then((result) => {
            userGroups = result;
            renderUserGroup();
        });
    });
    renderUserGroup();
};

const deleteUserGroup = (id) => {
    fetch(`${URL}/usergroup/${id}`, {
        method: 'DELETE',
        headers:{
            'Authorization': sessionStorage.getItem("auth")
        }
    }).then((result) => {
        indexUserGroup();
    });
};

const updateApplicationUsers = (userGroup) => {
    fetch(`${URL}/usergroup/${userGroup.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("auth")
        },
        body: JSON.stringify(userGroup)
    }).then((result) => {
        result.json().then((userGroup) => {
            userGroups = userGroups.map((e) => e.id === userGroup.id ? userGroup : e);
            renderUserGroup();
        });
    });
}

// Rendering
const resetForm = () => {
    const usersGroupForm = document.querySelector('#userGroupForm');
    usersGroupForm.reset();
    mode = 'create';
    currentUserGroup = null;
}

const saveForm = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const userGroup = {};
    userGroup['userGroup'] = formData.get('userGroupName');



    if (mode === 'create') {
        createUserGroup(userGroup);
    } else {
        userGroup.id = currentUserGroup.id;
        updateApplicationUsers(userGroup);
    }
    resetForm();
}

const editUserGroup = (userGroup) => {
    mode = 'edit';
    currentUserGroup = userGroup;


    const userGroupForm = document.querySelector('#userGroupForm');
    const userGroupField = userGroupForm.querySelector('[name="userGroupName"]');
    userGroupField.value = userGroup.userGroup;
}

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const createActions = (userGroup) => {
    const cell = document.createElement('td');

    const deleteButton = document.createElement('button');
    deleteButton.innerText = 'Delete';
    deleteButton.addEventListener('click', () => deleteUserGroup(userGroup.id));
    cell.appendChild(deleteButton);

    const editButton = document.createElement('button');
    editButton.innerText = 'Edit';
    editButton.addEventListener('click', () => editUserGroup(userGroup));
    cell.appendChild(editButton);

    return cell;
}

const renderUserGroup = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    userGroups.forEach((userGroup) => {

        const row = document.createElement('tr');
        row.appendChild(createCell(userGroup.id));
        row.appendChild(createCell(userGroup.userGroup));
        row.appendChild(createActions(userGroup));
        display.appendChild(row);
    });
};

document.addEventListener('DOMContentLoaded', function(){
    const userGroupForm = document.querySelector('#userGroupForm');
    userGroupForm.addEventListener('submit', saveForm);
    userGroupForm.addEventListener('reset', resetForm);
    indexUserGroup();
});

