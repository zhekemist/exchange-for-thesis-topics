const REPORTS = [
    {
        name: "Supervisees Within a Research Group",
        endpoint: ""
    },
    {
        name: "Most Popular Topics per Category",
        endpoint: ""
    },
]

function fetchReport(reportEndpoint) {
    // return fetch(reportEndpoint, {mode: "cors"})
    //     .then(responseHandler)
    //     .catch(alertErrorHandler("Could not fetch the report results!"));
    return Promise.resolve({
        usedFilter: "Some Filter",
        columnNames: ["Spalte 1", "Spalte 2", "Spalte 3"],
        reportData: [
            {
                spalte1: "ABC",
                spalte2: "DEF",
                spalte3: "GHI"
            },
            {
                spalte1: "ABC",
                spalte2: "DEF",
                spalte3: "GHI"
            }
        ]
    });
}

export function createReportInformation() {
    return {
        reportIdx: this.$persist(null),
        usedFilter: null,
        columnNames: null,
        reportData: null,

        get currentReport() {
            return REPORTS[this.reportIdx];
        },

        openReport(reportIdx) {
            this.reportIdx = reportIdx;
            window.location.href = 'report.html';
        },

        loadReport() {
            fetchReport(this.currentReport.endpoint)
                .then(reportResult => {
                    this.usedFilter = reportResult.usedFilter;
                    this.columnNames = reportResult.columnNames;
                    this.reportData = reportResult.reportData;
                })
        }
    }
}
