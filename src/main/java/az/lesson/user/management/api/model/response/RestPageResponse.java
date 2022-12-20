package az.lesson.user.management.api.model.response;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestPageResponse<T> {
    private List<T> content;
    private Long totalElements;
    private Integer totalPages;
    private Integer pageIndex;
    private Integer pageSize;
    private Boolean next;
    private Boolean previous;
}
