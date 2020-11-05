package com.github.leemanong.template.activity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.github.leemanong.template.listeners.MyProjectManagerListener.Companion.projectInstance
import com.intellij.ide.util.PropertiesComponent
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import com.intellij.psi.impl.PsiManagerImpl
import com.intellij.psi.impl.file.PsiDirectoryImpl
import org.jetbrains.kotlin.idea.KotlinLanguage
import java.io.File

fun RecipeExecutor.fragmentSetup(
        moduleData: ModuleTemplateData,
        packageName: String,
        basePackageName:String,
        entityName: String,
        layoutName: String
) {
    val (projectData) = moduleData
    val project = projectInstance ?: return

    addAllKotlinDependencies(moduleData)
    PropertiesComponent.getInstance().setValues("basePackageName", arrayOf(basePackageName))
    val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
    val virtSrc = virtualFiles.first { it.path.contains("src") }
    val virtRes = virtualFiles.first { it.path.contains("res") }
    val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)!!
    //val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)!!
    // val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)!!

    someFragment(packageName,basePackageName, entityName, layoutName.toLowerCase(), projectData)
            .save(moduleData.srcDir, "${entityName}Fragment.kt",project)
    someContract(packageName, entityName, basePackageName)
            .save(moduleData.srcDir, "${entityName}Contract.kt",project)
    somePresenter(packageName, entityName, basePackageName)
            .save(moduleData.srcDir, "${entityName}Presenter.kt",project)


    val resFile = File(moduleData.resDir,"layout")
    if (!resFile.exists()) {
        resFile.mkdirs()
    }
    someFragmentLayout(packageName, entityName)
            .save(resFile, "${layoutName.toLowerCase()}.xml",project)
}

fun RecipeExecutor.activitySetup(
        moduleData: ModuleTemplateData,
        packageName: String,
        basePackageName:String,
        entityName: String,
        layoutName: String
) {
    val (projectData) = moduleData
    val project = projectInstance ?: return

    addAllKotlinDependencies(moduleData)
    PropertiesComponent.getInstance().setValues("basePackageName", arrayOf(basePackageName))
    val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
    val virtSrc = virtualFiles.first { it.path.contains("src") }
    val virtRes = virtualFiles.first { it.path.contains("res") }
    val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)!!
    //val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)!!
   // val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)!!

    /*NotificationGroup("Custom Notification Group", NotificationDisplayType.BALLOON, true)
            .createNotification(layoutName, NotificationType.INFORMATION).notify(projectInstance)*/

    someActivity(packageName,basePackageName, entityName, layoutName.toLowerCase(), projectData)
            .save(moduleData.srcDir, "${entityName}Activity.kt",project)
    someContract(packageName, entityName, basePackageName)
            .save(moduleData.srcDir, "${entityName}Contract.kt",project)
    somePresenter(packageName, entityName, basePackageName)
            .save(moduleData.srcDir, "${entityName}Presenter.kt",project)


    val resFile = File(moduleData.resDir,"layout")
    if (!resFile.exists()) {
        resFile.mkdirs()
    }
    someActivityLayout(packageName, entityName)
            .save(resFile, "${layoutName.toLowerCase()}.xml",project)
}

fun String.save(srcDir: PsiDirectory, subDirPath: String, fileName: String) {
    try {
        val destDir = subDirPath.split(".").toDir(srcDir)
        val psiFile = PsiFileFactory
                .getInstance(srcDir.project)
                .createFileFromText(fileName, KotlinLanguage.INSTANCE, this)
        destDir.add(psiFile)
    }catch (exc: Exception) {
        exc.printStackTrace()
    }
}

fun String.save(srcDir: File, fileName: String,project:Project) {
    try {
        val destDir = PsiDirectoryImpl(PsiManagerImpl(project), LocalFileSystem.getInstance().findFileByIoFile(srcDir)!!)
        val psiFile = PsiFileFactory
                .getInstance(project)
                .createFileFromText(fileName, KotlinLanguage.INSTANCE, this)
        destDir.add(psiFile)
    }catch (exc: Exception) {
        exc.printStackTrace()
    }
}

fun List<String>.toDir(srcDir: PsiDirectory): PsiDirectory {
    var result = srcDir
    forEach {
        result = result.findSubdirectory(it) ?: result.createSubdirectory(it)
    }
    return result
}