const API = "http://127.0.0.1:8081/api/empregados";


function cadastrar() {
    const empregado = {
        nome: document.getElementById("nome").value,
        email: document.getElementById("email").value,
        senha: document.getElementById("senha").value
    };

    fetch(`${API}/cadastrar`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(empregado)
})
.then(res => {
    if (res.ok) {
        alert("Cadastro realizado!");
        window.location.href = "index.html";
    } else {
        res.text().then(msg => alert("Erro ao cadastrar: " + msg));
    }
})
.catch(err => {
    alert("Erro na requisição: " + err.message);
});

}

function login() {
    const credenciais = {
        email: document.getElementById("email").value,
        senha: document.getElementById("senha").value
    };

    fetch(`${API}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(credenciais)
    })
    .then(res => res.json())
    .then(empregado => {
        if (empregado && empregado.id) {
            alert("Login realizado com sucesso!");
            localStorage.setItem("empregadoId", empregado.id);
            // redireciona para tela de registro de ponto futuramente
        } else {
            alert("Credenciais inválidas!");
        }
    });
}
