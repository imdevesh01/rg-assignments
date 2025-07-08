// task1: Initialize the variable 'transactions' as an empty array.
let transactions = [];

let isEditing = false;
let editingTransactionId = null;

document.getElementById('transactionForm').addEventListener('submit', async function (event) {
    event.preventDefault();

    // task2: Retrieve input values using document.getElementById().
    const payee = document.getElementById('payee').value;
    const amount = parseFloat(document.getElementById('amount').value);
    const reason = document.getElementById('reason').value;

    if (payee === '' || amount <= 0 || isNaN(amount)) {
        alert('Please enter valid transaction details or check your script code.');
        return;
    }

    if (isEditing) {
        updateTransaction(editingTransactionId, payee, amount, reason);
        isEditing = false;
        editingTransactionId = null;
        document.querySelector('button[type="submit"]').textContent = 'Add Transaction';
        await updateTotalTransactionAmount();
    } else {
        addTransaction(payee, amount, reason);
    }

    clearForm();
    updateTransactionTable();
    await updateTotalTransactionAmount();
});

function addTransaction(payee, amount, reason) {
    const id = transactions.length > 0 ? transactions[transactions.length - 1].id + 1 : 1;

    // task3: Complete the newTransaction object
    const newTransaction = {
        id: id,
        payee: payee,
        amount: amount,
        reason: reason
    };

    // task4: Add newTransaction to the transactions array
    transactions.push(newTransaction);
}

function updateTransaction(id, payee, amount, reason) {
    // task5: Find the transaction object by id
    const transaction = transactions.find(t => t.id === id);

    if (transaction) {
        transaction.payee = payee;
        transaction.amount = amount;
        transaction.reason = reason;
    }
}

function editTransaction(id) {
    const transaction = transactions.find(t => t.id === id);
    if (transaction) {
        document.getElementById('payee').value = transaction.payee;
        document.getElementById('amount').value = transaction.amount;
        document.getElementById('reason').value = transaction.reason;

        isEditing = true;
        editingTransactionId = id;
        document.querySelector('button[type="submit"]').textContent = 'Edit Transaction';
    }
}

async function deleteTransaction(id) {
    const index = transactions.findIndex(t => t.id === id);

    if (index !== -1) {
        // task6: Remove the transaction by index
        transactions.splice(index, 1);
        updateTransactionTable();
        await updateTotalTransactionAmount();
    }
}

// task7 & 8: Convert to async/await
async function updateTotalTransactionAmount() {
    let totalAmount = 0;
    transactions.forEach(transaction => {
        totalAmount += transaction.amount;
    });

    document.getElementById('totalTransactionAmount').textContent = totalAmount.toFixed(2);
}

function updateTransactionTable() {
    const tbody = document.querySelector('#transactionTable tbody');
    tbody.innerHTML = '';

    if (transactions.length === 0) {
        const noTransactionMessage = document.createElement('tr');
        noTransactionMessage.innerHTML = `<td colspan="6">No Transactions found.</td>`;
        tbody.appendChild(noTransactionMessage);
    } else {
        transactions.forEach(transaction => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${transaction.id}</td>
                <td>${transaction.payee}</td>
                <td>${transaction.amount.toFixed(2)}</td>
                <td>${transaction.reason}</td>
                <td><button class="edit-button" onclick="editTransaction(${transaction.id})">Edit</button></td>
                <td><button class="delete-button" onclick="deleteTransaction(${transaction.id})">Delete</button></td>
            `;
            tbody.appendChild(row);
        });
    }
}

function clearForm() {
    document.getElementById('payee').value = '';
    document.getElementById('amount').value = '';
    document.getElementById('reason').value = '';
}
