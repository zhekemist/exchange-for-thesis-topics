import {alertErrorHandler, responseHandler} from "./utils.mjs";

const REPORTS = [
    {
        name: "Supervisees Within a Research Group",
        endpoint: "http://localhost:8080/api/report/supervisees",
        columns: {
            firstName: "First Name",
            lastName: "Last Name",
            matriculationNumber: "Matriculation Nr.",
            email: "E-Mail",
            title: "Thesis Title",
            supervisorFirstName: "First Name (Supervisor)",
            supervisorLastName: "Last Name (Supervisor)"
        }
    },
    {
        name: "Most Popular Topics per Category",
        endpoint: "",
        columns: {}
    },
]

function fetchReport(reportEndpoint) {
    return fetch(reportEndpoint, {mode: "cors"})
        .then(responseHandler)
        .catch(alertErrorHandler("Could not fetch the report results!"));
}

export function createReportInformation() {
    return {
        reportIdx: this.$persist(null),
        usedFilter: null,
        reportData: null,

        get currentReport() {
            return REPORTS[this.reportIdx];
        },

        get columns() {
            return REPORTS[this.reportIdx].columns;
        },

        openReport(reportIdx) {
            this.reportIdx = reportIdx;
            this.columns = REPORTS[reportIdx].columns;
            window.location.href = 'report.html';
        },

        loadReport() {
            console.log(this.columns)
            fetchReport(this.currentReport.endpoint)
                .then(reportResult => {
                    this.usedFilter = reportResult.usedFilter;
                    this.reportData = reportResult.reportData;
                })
        }
    }
}
