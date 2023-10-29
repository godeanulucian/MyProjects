
// UserDiv actions
document.addEventListener("DOMContentLoaded", function () {
    // button constants
    const personalInfoBtn = document.getElementById("personalInfoBtn");
    const cardInfoBtn = document.getElementById("cardInfoBtn");
    const paymentsBtn = document.getElementById("paymentsBtn");
    const addMoneyBtn = document.getElementById("addMoneyBtn");
    const sendMoneyBtn = document.getElementById("sendMoneyBtn");

    // data constants
    const personalInfo = document.getElementById("personalInfo");
    const cardInfo = document.getElementById("cardInfo");
    const payments = document.getElementById("payments");
    const addMoney = document.getElementById("addMoneyDiv");
    const sendMoney = document.getElementById("sendMoneyDiv");

    personalInfoBtn.addEventListener("click", function () {
        personalInfo.classList.toggle("show");
    });

    cardInfoBtn.addEventListener("click", function () {
        cardInfo.classList.toggle("show");
    });

    paymentsBtn.addEventListener("click", function () {
        payments.classList.toggle("show");
    });

    addMoneyBtn.addEventListener("click", function () {
        addMoney.classList.toggle("show");
    });

    sendMoneyBtn.addEventListener("click", function () {
        sendMoney.classList.toggle("show");
    });

});

