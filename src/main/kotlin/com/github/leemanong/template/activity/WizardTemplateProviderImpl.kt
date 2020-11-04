package com.github.leemanong.template.activity

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider

class WizardTemplateProviderImpl  : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(mviSetupActivityTemplate,setUpFragmentTemplate)
}