package com.ian.bskyapp.entity;

import java.util.List;

public record Embed(
        String $type,
        Record record,
        Media media,
        External external,
        List<Images> images) {
}
