package at.ac.univie.imse.backend.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReportResult<T> {
    private String usedFilter;
    private Iterable<T> reportData;
}
