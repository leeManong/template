package com.github.leemanong.template.activity

import com.android.tools.idea.wizard.template.*
import com.intellij.ide.util.PropertiesComponent

val setUpFragmentTemplate
    get() = template {
        revision = 2
        name = "MVP Fragment"
        description = "Creates a new fragment along layout file."
        minApi = 16
        minBuildApi = 16
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
                WizardUiContext.NewProject, WizardUiContext.NewModule)

        val packageNameParam = defaultPackageNameParameter
        val entityName = stringParameter {
            name = "Entity Name"
            default = ""
            help = "The name of the entity class to create and use in Fragment"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "my_act"
            help = "The name of the layout to create for the fragment"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { fragmentToLayout(entityName.value.toLowerCase()) }
        }

        val basePackageName = stringParameter {
            name = "Base Package name"
            default = PropertiesComponent.getInstance().getValue("basePackageName","com.mycompany.myapp")
            constraints = listOf(Constraint.NONEMPTY)
        }

        widgets(
                TextFieldWidget(entityName),
                TextFieldWidget(layoutName),
                PackageNameWidget(packageNameParam),
                TextFieldWidget(basePackageName)
        )
        recipe = { data: TemplateData ->
            fragmentSetup(
                    data as ModuleTemplateData,
                    packageNameParam.value,
                    basePackageName.value,
                    entityName.value,
                    layoutName.value
            )
        }
    }

val mviSetupActivityTemplate
    get() = template {
        revision = 2
        name = "MVP Activity"
        description = "Creates a new activity along layout file."
        minApi = 16
        minBuildApi = 16
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
                WizardUiContext.NewProject, WizardUiContext.NewModule)

        val packageNameParam = defaultPackageNameParameter
        val entityName = stringParameter {
            name = "Entity Name"
            default = ""
            help = "The name of the entity class to create and use in Activity"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "my_act"
            help = "The name of the layout to create for the activity"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { activityToLayout(entityName.value.toLowerCase()) }
        }

        val basePackageName = stringParameter {
            name = "Base Package name"
            default = PropertiesComponent.getInstance().getValue("basePackageName","com.mycompany.myapp")
            constraints = listOf(Constraint.NONEMPTY)
        }

        widgets(
                TextFieldWidget(entityName),
                TextFieldWidget(layoutName),
                PackageNameWidget(packageNameParam),
                TextFieldWidget(basePackageName)
        )
        recipe = { data: TemplateData ->
            activitySetup(
                    data as ModuleTemplateData,
                    packageNameParam.value,
                    basePackageName.value,
                    entityName.value,
                    layoutName.value
            )
        }
    }

val defaultPackageNameParameter get() = stringParameter {
    name = "Package name"
    visible = { !isNewModule }
    default = "com.mycompany.myapp"
    constraints = listOf(Constraint.PACKAGE)
    suggest = { packageName }
}