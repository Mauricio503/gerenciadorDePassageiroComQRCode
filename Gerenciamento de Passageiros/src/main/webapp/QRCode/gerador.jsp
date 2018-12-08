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

		<main class="Main">
		<div class="block-inner">

			<section class="Section">
			<div class="Section-header">
				<p class="Section-subTitle">QR Code</p>
			</div>
			<div class="Section-content">

				<form action='<c:url value="/QRCode"/>' method="post">
					<table class="table">
						<thead>
							<tr>
								<th>Nome</th>
								<th>Cpf</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${clientes}" var="cliente">
								<tr>
									<td><c:out value="${cliente.nomeCompleto}"></c:out></td>
									<td><c:out value="${cliente.cpf}"></c:out></td>
									<td>
									<a class="btn btn-primary" type="button" href="QRCode?codigo=${cliente.id}">Gerar</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
				</form>
			</div>
			</section>

		</div>
		</main>
	</div>


</body>
</html>