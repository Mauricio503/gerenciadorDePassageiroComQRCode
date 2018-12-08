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
			<title>Gerenciamento de Passageiros</title> <script
				src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
			<link href="./css/style.css" rel="stylesheet">
				<link href="./css/bootstrap.min.css" rel="stylesheet">

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
				<p class="Section-subTitle">Editar Cliente</p>
			</div>
			<div class="Section-content">
				<form action='<c:url value="/alterarCliente"/>' method="post">
					<input type="text" style="display: none;" value="${cliente.id}"
						name="id" id="id" class="form-control">
					<div class="form-group col-sm-6">
						<label for="nome">Nome Completo:</label> <input type="text"
							value="${cliente.nomeCompleto}" name="nome" id="nome"
							class="form-control">
					</div>
					<div class="form-group col-sm-6">
						<label for="cpf">Cpf:</label> <input type="text" name="cpf"
							value="${cliente.cpf}" id="cpf" class="form-control">
					</div>
					<div class="form-group col-sm-6">
						<label for="endereco">Endereco:</label> <input type="text"
							value="${cliente.endereco}" name="endereco" id="endereco"
							class="form-control">
					</div>
					<div class="form-group col-sm-6">
						<label for="cidade">Cidade:</label> <input type="text"
							value="${cliente.cidade}" name="cidade" id="cidade"
							class="form-control">
					</div>
					<div class="form-group col-sm-6">
						<label for="cep">Cep:</label> <input type="text" name="cep"
							value="${cliente.cep}" id="cep" class="form-control">
					</div>
					<div class="form-group col-sm-6">
							<label for="telefone">Telefone:</label> <input type="text" name="telefone"
								id="telefone" class="form-control" value="${cliente.telefone}" onKeyPress="mascaraTelefone(value);"
							>
						</div>
					<div class="form-group col-sm-6">
						<label for="email">E-mail:</label> <input type="text"
							value="${cliente.email}" name="email" id="email"
							class="form-control">
					</div>
					<div class="form-group col-sm-6">
						<label for="valor">Valor:</label> <input type="text"
							value="${cliente.valor}" requerido="true" name="valor" id="valor"
							class="form-control">
					</div>
					<div class="form-group col-sm-6">
					<label for="gerarCobranca">Gerar Cobrança?</label>&nbsp;
						<c:if test="${cliente.gerarCobranca == true}">
							<input type='checkbox' name='fazerCobranca' id="fazerCobranca" checked="true"/>
						</c:if>		
						<c:if test="${cliente.gerarCobranca == false}">
							<input type='checkbox' name='' id="fazerCobranca"/>
						</c:if>
					</div>
					<div class="row"></div>
					<div class="col-sm-6">
						<input type="submit" value="Salvar" class="btn btn-primary"
							onclick="validar()"> <a href="cliente"> <input
								type="button" class="btn btn-light" value="Voltar"></input>
						</a>
						<a href="cliente?codigo=${cliente.id}"> <input
								type="button" class="btn btn-danger" value="Excluir"></input>
						</a>
					</div>
				</form>
			</div>
			</section>

		</div>
		</main>
	</div>


</body>
</html>
