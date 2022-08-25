package com.hanghae99.sulmocco.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.hanghae99.sulmocco.model.Bookmark;
import com.hanghae99.sulmocco.model.Tables;
import lombok.*;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
//앞단에 내가북마크한TABLES 표시하기위해 필요한 DTO
public class BookmarkResponseDto {
    private String title;
    private String username;
    private String content;
    private Long tablesId;
    private int likecount;  //@OneToMany(mappedBy = "tables", cascade = CascadeType.ALL //private List<Like> likes; 사이즈로 갖고옴.
    private int viewcount;  //콜렉션으로 유저ID 저장 // 사이즈로 갖고옴.
    private String alcoholtag;
    private String freetag;
    private String profileimgurl; // 작성자 profileimg
    private String thumbnail;
    private LocalDateTime createdAt;

    public static Slice<BookmarkResponseDto> myPageBookmarkResponseDto(Slice<Bookmark> bookmarkSlice) {
            Slice<BookmarkResponseDto> bookmarkResponseDtoSlice = bookmarkSlice.map(t ->
                            BookmarkResponseDto.builder()
                                    .title(t.getTables().getTitle())
                                    .username(t.getTables().getUser().getUsername())
                                    .content(t.getTables().getContent())
                                    .tablesId(t.getTables().getId())
                                    .likecount(t.getTables().getLikes().size())
                                    .viewcount(t.getTables().getViewUserList().size())
                                    .alcoholtag(t.getTables().getAlcoholTag())
                                    .freetag(t.getTables().getFreeTag())
                                    .profileimgurl(t.getTables().getUser().getProfileUrl())
                                    .thumbnail(t.getTables().getThumbnailImgUrl())
                                    .createdAt(t.getTables().getCreatedAt())
                                    .build()
            );
            return bookmarkResponseDtoSlice;
    }
}



//     “lastPage”: “마지막 페이지 여부”,
//             “tables":[
//             {
//             “tableId”: “술상 id”
//             “thumbnail”:”썸네일 이미지 url”,
//             “title” : “제목”
//             “username” : “사용자 이름”,
//             “likecount”: “추천수”,
//             “viewcount”: “조회수”,
//             “alcoholtag” : “추천술태그”,
//             “freetag” : “자유태그”,
//             “profileimgurl” : “작성자 프로필 이미지”