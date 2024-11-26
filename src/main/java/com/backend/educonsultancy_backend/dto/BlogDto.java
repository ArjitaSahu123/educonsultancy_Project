//package com.backend.educonsultancy_backend.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class BlogDto {
//
//    private Integer blogId;
//    private Integer userId;
//    private String title;
//    private String content;
//    private String category;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//    private String blogImage;
//    private String blogUrl; // Generated URL for accessing the image
//}



package com.backend.educonsultancy_backend.dto;

import com.backend.educonsultancy_backend.auth.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    private Integer blogId;  // Primary Key for the Blog entity

    //============== CHANGED CODE ========================================
    //    @NotNull(message = "Author (user) is mandatory")
    //    private User user;  // Author of the blog (consultant/admin)
    // Replace 'User' with 'userId' (Integer type)
    @NotNull(message = "Author (user) is mandatory")
    private Integer userId;  // Instead of User, use userId (Integer)
    //====================================================================

    @NotBlank(message = "Title is mandatory")
    private String title;  // Blog title

    @NotBlank(message = "Content is mandatory")
    private String content;  // Content of the blog (can be large)

    @NotBlank(message = "Category is mandatory")
    private String category;  // Category of the blog (e.g., Technology, Education, etc.)

    private LocalDateTime createdAt;  // Timestamp of when the blog was created

    private LocalDateTime updatedAt;  // Timestamp of when the blog was last updated

    @NotBlank(message = "Blog image is mandatory")
    private String blogImage;  // Blog image URL

    @NotBlank(message = "Blog URL is mandatory")
    private String blogUrl;
}
