import {createReportInformation} from "./scripts/reports.mjs";

document.addEventListener('alpine:init', () => {
    Alpine.data('reportInformation', createReportInformation)
});
