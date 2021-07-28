package com.example.tocomdor;

import java.util.*;

public class Formulario {
    List<String> perSN;
    Hashtable<String, Boolean> perText;     // Pergunta, resposta númerica
    List<Pergunta> perMult;

    public Formulario() {

        //perguntas de S/N
        perSN = new ArrayList<>();

        perSN.add("Teve febre nos últimos 7 dias?");
        perSN.add("Teve calafrios nos últimos 7 dias?");
        perSN.add("Teve dor de garganta nos últimos 7 dias?");
        perSN.add("Teve dor de cabeça nos últimos 7 dias?");
        perSN.add("Teve tosse nos últimos 7 dias?");
        perSN.add("Teve coriza nos últimos 7 dias?");
        perSN.add("Teve distúrbios olfativos nos últimos 7 dias?");
        perSN.add("Teve distúrbios gustativos nos últimos 7 dias?");
        perSN.add("O paciente teve contato próximo com uma pessoa que seja caso SUSPEITO de Novo Coronavírus (Covid-19)?");
        perSN.add("O paciente teve contato próximo com uma pessoa que seja caso CONFIRMADO de Novo Coronavírus (Covid-19)?");
        perSN.add("Esteve em alguma unidade de saúde e/ou hospital (UBS) nos 14 dias antes do início dos sintomas?");
        perSN.add("Foi vacinada?");

        //perguntas de caixa de texto
        perText = new Hashtable<String, Boolean>();

        perText.put("Qual a idade do paciente?", true);

        //perguntas de multiplas respostas
        perMult = new ArrayList<>();

        String perMulTemp = "Você possui alguma comorbidade";
        List<String> res = new ArrayList<>();
        res.add("Diabetes Mellitus");
        res.add("Hipertensão Arterial Sistêmica/Pressão Alta");
        res.add("Cardiopatias");
        res.add("Imunodeprimimidos (uso contínuo de corticoides, HIV, diálise/hemodiálise, quimioterapia)");
        res.add("Gestante");
        res.add("Puérpera (mulher que teve filho até 42º dia pós parto)");
        res.add("Doenças respiratórias crônicas (enfisema pulmonar, asma, bronquite)");

        Pergunta perTemp = new Pergunta(perMulTemp, res);
        perMult.add(perTemp);
    }

    public List<String> getPerSN() {
        return perSN;
    }

    public Hashtable<String, Boolean> getPerText() {
        return perText;
    }

    public List<Pergunta> getPerMult() {
        return perMult;
    }
}
