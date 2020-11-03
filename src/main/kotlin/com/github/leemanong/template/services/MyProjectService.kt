package com.github.leemanong.template.services

import com.intellij.openapi.project.Project
import com.github.leemanong.template.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
