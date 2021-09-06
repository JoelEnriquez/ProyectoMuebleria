let dataTable = document.getElementById("tabla_exportar");

new ExportadorTablaCSV(dataTable, true).convertToCSV();

$("#boton_exportar").on("click", function () {
    const exporter = new ExportadorTablaCSV(dataTable);
    const csvOutput = exporter.convertToCSV();
    const csvBlob = new Blob([csvOutput], {type: "text/csv"});
    const blobUrl = URL.createObjectURL(csvBlob);
    const anchorElement = document.createElement("a");

    anchorElement.href = blobUrl;
    anchorElement.download = "Exportar-Reporte.csv";
    anchorElement.click();

    setTimeout(() => {
        URL.revokeObjectURL(blobUrl);
    }, 500);
});


