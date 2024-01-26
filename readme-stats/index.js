const express = require("express");
const fs = require("fs").promises;

const app = express();
const port = 3000;

const updateData = async (username) => {
    try {
        const apiUrl = `http://localhost:8080/api/stats/${username}`;
        const fetch = await import("node-fetch");

        const response = await fetch.default(apiUrl);

        if (!response.ok) {
            throw new Error(`Erro na requisição à API: ${response.status}`);
        }

        const responseData = await response.json();

        if (responseData && responseData.data) {
            const data = responseData.data;

            let htmlTemplate = await fs.readFile("index.html", "utf-8");

            htmlTemplate = htmlTemplate.replace("COMMITS_PLACEHOLDER", data.commits);
            htmlTemplate = htmlTemplate.replace("ISSUES_PLACEHOLDER", data.issues);
            htmlTemplate = htmlTemplate.replace("PRS_PLACEHOLDER", data.pullRequests);

            return htmlTemplate;
        } else {
            throw new Error("Resposta da API não contém dados válidos.");
        }
    } catch (error) {
        throw error;
    }
};

app.get("/:username", async (req, res) => {
    try {
        const username = req.params.username;
        const htmlTemplate = await updateData(username);
        res.send(htmlTemplate);
    } catch (error) {
        console.error("Erro durante o processamento da requisição:", error);
        res.status(500).send("Erro interno do servidor");
    }
});

app.listen(port, () => {
    console.log(`server is live!`);
});
