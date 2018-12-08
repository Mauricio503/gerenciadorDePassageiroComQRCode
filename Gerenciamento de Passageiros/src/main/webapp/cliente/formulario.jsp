<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="generator" content="Hugo 0.39" />
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
			<title>Gerenciamento de Passageiros</title>

			<link href="style.css" rel="stylesheet">
			<link href="bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="Wrapper">
		<div class="Sidebar" style="background: #102c58">
			<header class="Header">
			<div class="Header-logo">
				<img src="img/logo.png" alt="Logo" style="width: 50%"/>
			</div>
			</header>
			<nav class="Nav">
			<ul class="Nav-items">
				<li class="Nav-item Nav-item--isActive"><a style="text-decoration:none"
					href="/projetoSite2/cliente" class="Nav-link">Cliente</a></li>

				<li class="Nav-item "><a style="text-decoration:none" href="/projetoSite2/QRCode"
					class="Nav-link">Gerar QR Code</a></li>
					
				<li class="Nav-item "><a style="text-decoration:none" href="https://mauriciogspagnol.000webhostapp.com/docs/index.html"
					class="Nav-link">Leitor QR Code</a></li>
					
				<li class="Nav-item "><a style="text-decoration:none" href="/projetoSite2/controlePresenca"
					class="Nav-link">Controle De Presença</a></li>			

				<li class="Nav-item "><a style="text-decoration:none" href="/projetoSite2/financeiroCobranca"
					class="Nav-link">Financeiro</a></li>

				<li class="Nav-item "><a style="text-decoration:none" href="/projetoSite2/relatorioFinanceiro"
					class="Nav-link">Relatório Financeiro</a></li>
					
				<li class="Nav-item "><a style="text-decoration:none" href="/projetoSite2/relatorioPresenca"
					class="Nav-link">Relatório Presença</a></li>
			</ul>
			</nav>
			<div id="carbonad" class="CarbonAd"></div>
		</div>

<script type="text/javascript">
window.onload = function(){
	var div = document.getElementById("fazerCobranca");
	var fazerC = document.getElementById("fazerC").value;
	if(fazerC == true){
		div.setAttribute('checked',"");
	}
}
function mascaraCep(cep){
	if (cep.length == 2){
		cep = cep+"."; }
	if (cep.length == 6){
		cep = cep+"-"; }
	document.getElementById("cep").value = cep;
	};
	
function mascaraCpf(cpf){
	if(cpf.length == 3){
		cpf = cpf+".";}
	if(cpf.length == 7){
		cpf = cpf+".";}
	if(cpf.length == 11){
		cpf = cpf+"-";}
	document.getElementById("cpf").value = cpf;
};
function mascaraTelefone(telefone){
	if(telefone.length == 0){
		telefone = "("+telefone;}
	if(telefone.length == 3){
		telefone = telefone+")";}
	document.getElementById("telefone").value = telefone;
};
function validar() {
	var valor = document.getElementById("valor").value;

	if (valor == "") {
		alert('Preencha o campo com seu valor');
	}
	
};
</script>

		<main class="Main">
		<div class="Main-inner">

			<section class="Section">
			<div class="Section-header">
				<p class="Section-subTitle">Cobrança</p>
			</div>
			<div class="Section-content">
					<form action='<c:url value="/cliente"/>' method="post">
						<div class="form-group col-sm-6">
							<label for="nome">Nome Completo:</label> <input type="text"
								name="nome" id="nome" class="form-control">
						</div>
						<div class="form-group col-sm-6">
							<label for="cpf">Cpf:</label> <input type="text" name="cpf"
								id="cpf" class="form-control" onKeyPress="mascaraCpf(value);">
						</div>
						<div class="form-group col-sm-6">
							<label for="endereco">Endereco:</label> <input type="text"
								name="endereco" id="endereco" class="form-control">
						</div>
						<div class="form-group col-sm-6">
							<label for="cidade">Cidade:</label> <input type="text"
								name="cidade" id="cidade" class="form-control">
						</div>
						<div class="form-group col-sm-6">
							<label for="cep">Cep:</label> <input type="text" name="cep"
								id="cep" class="form-control" onKeyPress="mascaraCep(value);"
							>
						</div>
						
						<div class="form-group col-sm-6">
							<label for="telefone">Telefone:</label> <input type="text" name="telefone"
								id="telefone" class="form-control" onKeyPress="mascaraTelefone(value);"
							>
						</div>
						<div class="form-group col-sm-6">
							<label for="email">E-mail:</label> <input type="text"
								name="email" id="email" class="form-control">
						</div>
						<div class="form-group col-sm-6">
							<label for="valor">Valor:</label> <input type="text"
								name="valor" id="valor" class="form-control">
						</div>
						<div class="form-group col-sm-6">
							<label for="gerarCobranca">Gerar Cobrança?</label>&nbsp;
							<input type="hidden" id="fazerC" value="${cliente.gerarCobranca}"></input>
							<input type='checkbox' name='fazerCobranca' checked/>
						</div>
						<div class="row"></div>
						<input type="submit" value="Salvar" class="btn btn-primary" onclick="validar()">
						 <a onClick="history.go(-1)"><input type="button" class="btn btn-light" value="Voltar"></input></a>
					</form>
				</div>
			</section>

		</div>
		</main>
	</div>


</body>
</html>
