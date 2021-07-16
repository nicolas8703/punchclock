const URL = 'http://localhost:8081';
let applicationUsers = [];
let mode = 'create';
let currentApplicationUser;



// API Requests
const createApplicationUser= (applicationUser) => {
    fetch(`${URL}/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("auth")
        },
        body: JSON.stringify(applicationUser)
    }).then((result) => {
        result.json().then((applicationUser) => {
            applicationUsers.push(applicationUser);
            renderApplicationUser();
        });
    });
};

const indexapplicationUser = () => {
    fetch(`${URL}/users`, {
        method: 'GET',
        headers:{
            'Authorization': sessionStorage.getItem("auth")
        }
    }).then((result) => {
        result.json().then((result) => {
            applicationUsers = result;
            renderApplicationUser();
        });
    });
    renderApplicationUser();
};

const deleteApplicationUsers = (id) => {
    fetch(`${URL}/users/${id}`, {
        method: 'DELETE',
        headers:{
            'Authorization': sessionStorage.getItem("auth")
        }
    }).then((result) => {
        indexapplicationUser();
    });
};

const updateApplicationUsers = (applicationUser) => {
    fetch(`${URL}/users/${entry.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("auth")
        },
        body: JSON.stringify(applicationUser)
    }).then((result) => {
        result.json().then((applicationUser) => {
            applicationUsers = applicationUsers.map((e) => e.id === applicationUser.id ? applicationUser : e);
            renderApplicationUser();
        });
    });
}

// Rendering
const resetForm = () => {
    const applicationUsersForm = document.querySelector('#userForm');
    applicationUsersForm.reset();
    mode = 'create';
    currentApplicationUser = null;
}

const saveForm = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const applicationUser = {};
    applicationUser['username'] = formData.get('uname');
    applicationUser['password'] = formData.get('pw');

    if (mode === 'create') {
        createApplicationUser(applicationUser);
    } else {
        applicationUser.id = currentApplicationUser.id;
        updateApplicationUsers(applicationUser);
    }
    resetForm();
}

const editApplicationUser = (applicationUser) => {
    mode = 'edit';
    currentApplicationUser = applicationUser;

    //TODO
    //const userForm = document.querySelector('#userForm');
    //const checkInDateField = userForm.querySelector('[name="checkInDate"]');
    //checkInDateField.value = applicationUser.checkIn.split('T')[0];
    //const checkInTimeField = userForm.querySelector('[name="checkInTime"]');
    //checkInTimeField.value = applicationUser.checkIn.split('T')[1].slice(0, -3);
    //const checkOutDateField = userForm.querySelector('[name="checkOutDate"]');
    //checkOutDateField.value = applicationUser.checkOut.split('T')[0];
    //const checkOutTimeField = userForm.querySelector('[name="checkOutTime"]');
    //checkOutTimeField.value = applicationUser.checkOut.split('T')[1].slice(0, -3);
}

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const createActions = (applicationUser) => {
    const cell = document.createElement('td');

    const deleteButton = document.createElement('button');
    deleteButton.innerText = 'Delete';
    deleteButton.addEventListener('click', () => deleteApplicationUsers(applicationUser.id));
    cell.appendChild(deleteButton);

    const editButton = document.createElement('button');
    editButton.innerText = 'Edit';
    editButton.addEventListener('click', () => editApplicationUser(applicationUser));
    cell.appendChild(editButton);

    return cell;
}

const renderApplicationUser = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    applicationUsers.forEach((applicationUser) => {

        const row = document.createElement('tr');
        row.appendChild(createCell(applicationUser.id));
        row.appendChild(createCell(applicationUser.username));
        row.appendChild(createCell(applicationUser.password));
        row.appendChild(createActions(applicationUser));
        display.appendChild(row);
    });
};

document.addEventListener('DOMContentLoaded', function(){
    const userForm = document.querySelector('#userForm');
    userForm.addEventListener('submit', saveForm);
    userForm.addEventListener('reset', resetForm);
    indexapplicationUser();
});
