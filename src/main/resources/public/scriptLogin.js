const URL = 'http://localhost:8081';
let entries = [];
let switchmode = {
    update: false,
    id: null
}
let mode = 'create';
let currentEntry;

const createEntry = (e) => {

};

const registerBtnAction = (e) => {
    window.location.href = "register.html";
}

const saveForm = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['username'] = formData.get('uname1');
    entry['password'] = formData.get('pw1');

    fetch(`${URL}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        sessionStorage.setItem("auth",result.headers.get("Authorization"));
            window.location.href = "index.html";
        });
};

document.addEventListener('DOMContentLoaded', function(){
    const registerbtn = document.getElementById("registerbtn");
    //const submitBtn = document.getElementById("submitBtn");
    const entryForm = document.querySelector('#entryForm');
    entryForm.addEventListener('submit', saveForm);
    registerbtn.addEventListener('click', registerBtnAction);
    //submitBtn.addEventListener('submit', saveForm);
});
