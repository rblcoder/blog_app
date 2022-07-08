package com.company.blog;

import com.company.blog.entity.Tag;
import com.company.blog.repository.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TagRepoTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    void testGetPostsForATag() {
        Tag tag = tagRepository.findById(1L).orElse(null);
        Assertions.assertEquals(2, tag.getPostsList().size());
    }
}
