-- MySQL Script generated by MySQL Workbench
-- Wed Jul 21 13:54:03 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema journey
-- -----------------------------------------------------
-- Journey 博客系统
DROP SCHEMA IF EXISTS `journey`;

-- -----------------------------------------------------
-- Schema journey
--
-- Journey 博客系统
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `journey` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `journey`;



DROP TABLE IF EXISTS `blog_post`;
CREATE TABLE `blog_post` (
                             `id` varchar(36) COLLATE utf8_bin NOT NULL,
                             `title` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '博客标题',
                             `content_abstract` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '博客概要',
                             `content` text COLLATE utf8_bin COMMENT '博客内容',
                             `create_time` datetime DEFAULT NULL COMMENT '博客创建时间',
                             `update_time` datetime DEFAULT NULL COMMENT '博客上次更新时间',
                             `is_published` tinyint(1) unsigned DEFAULT NULL COMMENT '博客是否发布，0-否、1-是',
                             `publish_time` datetime DEFAULT NULL COMMENT '博客发布时间',
                             `is_original` tinyint(1) unsigned DEFAULT NULL COMMENT '是否原创，0-否、1-是',
                             `category_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '博客分类ID',
                             `tag_ids` varchar(330) COLLATE utf8_bin DEFAULT NULL COMMENT '博客标签ID列表',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='博客表';


DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
                                 `id` varchar(36) COLLATE utf8_bin NOT NULL,
                                 `name` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '分类名称',
                                 `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '分类描述',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='博客分类表';


DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
                            `id` varchar(36) COLLATE utf8_bin NOT NULL,
                            `name` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '标签名称',
                            `is_group` tinyint(4) DEFAULT '0' COMMENT '是否是组标签,0-否，1-是',
                            `group_id` varchar(36) COLLATE utf8_bin DEFAULT NULL COMMENT '所属标签组ID',
                            `category_id` varchar(36) COLLATE utf8_bin DEFAULT NULL COMMENT '标签所属分类ID',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='博客标签表';



SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
