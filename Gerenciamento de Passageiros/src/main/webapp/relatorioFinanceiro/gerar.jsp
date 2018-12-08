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
				<img src="img/logo.png" alt="Logo" style="width: 50%" />
			</div>
			</header>
			<nav class="Nav">
			<ul class="Nav-items">
				<li class="Nav-item Nav-item--isActive"><a
					style="text-decoration: none" href="/projetoSite2/cliente"
					class="Nav-link">Cliente</a></li>

				<li class="Nav-item "><a style="text-decoration: none"
					href="/projetoSite2/QRCode" class="Nav-link">Gerar QR Code</a></li>

				<li class="Nav-item "><a style="text-decoration: none"
					href="https://mauriciogspagnol.000webhostapp.com/docs/index.html"
					class="Nav-link">Leitor QR Code</a></li>

				<li class="Nav-item "><a style="text-decoration: none"
					href="/projetoSite2/controlePresenca" class="Nav-link">Controle
						De Presença</a></li>

				<li class="Nav-item "><a style="text-decoration: none"
					href="/projetoSite2/financeiroCobranca" class="Nav-link">Financeiro</a></li>

				<li class="Nav-item "><a style="text-decoration: none"
					href="/projetoSite2/relatorioFinanceiro" class="Nav-link">Relatório
						Financeiro</a></li>

				<li class="Nav-item "><a style="text-decoration: none"
					href="/projetoSite2/relatorioPresenca" class="Nav-link">Relatório
						Presença</a></li>
			</ul>
			</nav>

			<div id="carbonad" class="CarbonAd"></div>
		</div>

		<main class="Main">
		<div class="block-inner">

			<section class="Section">
			<div class="Section-header">
				<p class="Section-subTitle">Relatório Financeiro</p>
			</div>
			<div class="Section-content">
				<form action='<c:url value="relatorioFinanceiro"/>' method="post">
					<div class="col-sm-8">
						<div class="col-sm-2">
							<label>Mês/Ano:</label>
						</div>
						<div class="col-sm-5">
							<select name="mes" class="form-control">
									<option></option>
								<c:forEach items="${meses}" var="m">
									<option value="${m.getValue()}">${m.getValue()}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-3">
							<input value="${ano}" name="ano" class="form-control" />
						</div>
					</div>
					<div class="row"></div>
					<div class="col-sm-12" style="margin-top: 10px;">
						<div class="col-sm-1">
							<label>Cliente:</label>
						</div>
						<div class="col-sm-5">
							<select name="cliente" class="form-control">
								<option></option>
								<c:forEach items="${clientes}" var="c">
									<option value="${c.id}">${c.nomeCompleto}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-4">
							<div class="col-sm-4">
								<label>Situação:</label>
							</div>
							<div class="col-sm-8">
								<select class="form-control" name="situacao">
									<option></option>
									<option value="pago">Pago</option>
									<option value="pendente">Pendente</option>
								</select>
							</div>
						</div>
					</div>
					<input type="submit" value="Gerar" class="btn btn-primary">
				</form>
			</div>
			</section>

		</div>
		</main>
	</div>


</body>
</html>
