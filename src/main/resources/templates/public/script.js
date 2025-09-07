document.addEventListener('DOMContentLoaded', () => {

    if (document.querySelector('#login-form')) {
        initLoginPage();
    }
    if (document.querySelector('.app-layout')) {
        initConsolePage();
    }
});

function initLoginPage() {
    const form = document.getElementById('login-form');
    const errorMessage = document.getElementById('error-message');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        errorMessage.textContent = '';
        const username = form.username.value;
        const password = form.password.value;

        const response = await fetch('/api/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });

        const result = await response.json();
        if (result.status === 'success') {
            window.location.href = '/';
        } else {
            errorMessage.textContent = result.message || 'Bir hata oluştu.';
        }
    });
}

function initConsolePage() {
    const editor = CodeMirror.fromTextArea(document.getElementById('sql-editor'), {
        mode: 'text/x-sql',
        theme: 'ayu-dark',
        lineNumbers: true
    });

    editor.setOption("extraKeys", {
        "Ctrl-Enter": () => { document.getElementById('sql-form').requestSubmit(); }
    });

    const tableListNav = document.getElementById('table-list');
    const sqlForm = document.getElementById('sql-form');
    const resultsContent = document.getElementById('results-content');
    const messageContent = document.getElementById('message-content');
    const tabButtons = document.querySelectorAll('.tab-btn');


    const loadTables = async () => {
        const response = await fetch('/api/tables');
        const tables = await response.json();
        tableListNav.innerHTML = '';
        tables.forEach(tableName => {
            const link = document.createElement('a');
            link.href = '#';
            link.textContent = tableName;
            link.addEventListener('click', (e) => {
                e.preventDefault();
                editor.setValue(`SELECT * FROM ${tableName} LIMIT 100;`);
            });
            tableListNav.appendChild(link);
        });
    };


    tabButtons.forEach(button => {
        button.addEventListener('click', () => {
            tabButtons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');

            document.querySelectorAll('.tab-content').forEach(content => content.classList.add('hidden'));
            document.getElementById(button.dataset.tab + '-content').classList.remove('hidden');
        });
    });


    sqlForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const sql = editor.getValue();

        resultsContent.innerHTML = '';
        messageContent.innerHTML = '';

        const response = await fetch('/api/execute', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ sql })
        });

        const result = await response.json();

        if (result.status === 'success') {
            if (result.type === 'query') {
                displayResults(result.headers, result.rows);
                document.querySelector('[data-tab="results"]').click();
            } else {
                displayMessage(result.message, 'success');
                document.querySelector('[data-tab="message"]').click();
                loadTables();
            }
        } else {
            displayMessage(result.message, 'error');
            document.querySelector('[data-tab="message"]').click();
        }
    });

    function displayResults(headers, rows) {
        if (rows.length === 0) {
            resultsContent.innerHTML = '<p>Sorgu sonuç döndürmedi.</p>';
            return;
        }
        const table = document.createElement('table');
        table.className = 'result-table';
        const thead = table.createTHead();
        const headerRow = thead.insertRow();
        headers.forEach(h => headerRow.insertCell().textContent = h);

        const tbody = table.createTBody();
        rows.forEach(rowData => {
            const row = tbody.insertRow();
            rowData.forEach(cellData => row.insertCell().textContent = cellData);
        });
        resultsContent.appendChild(table);
    }

    function displayMessage(text, type) {
        const pre = document.createElement('pre');
        pre.className = `result-message ${type}`;
        pre.textContent = text;
        messageContent.appendChild(pre);
    }

    loadTables();
}