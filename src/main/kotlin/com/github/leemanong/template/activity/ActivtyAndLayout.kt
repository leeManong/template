package com.github.leemanong.template.activity

import com.android.tools.idea.wizard.template.ProjectTemplateData
import com.android.tools.idea.wizard.template.extractLetters

fun someFragment(
        packageName: String,
        basePackageName:String,
        entityName: String,
        layoutName: String,
        projectData: ProjectTemplateData
) = """package $packageName
import ${basePackageName}.mvp.BaseMvpFragment
import android.os.Bundle
import kotlinx.android.synthetic.main.${layoutName}.*
import ${projectData.applicationPackage}.R;

class ${entityName}Fragment : BaseMvpFragment<${entityName}Presenter>(), ${entityName}Contract.View {

    override fun initView() {
    }
    
    override fun initData() {
    }
    
    override fun initListener() {
    }
    
    override fun loadData() {
    }
    
    override fun bindLayout() = R.layout.${layoutName}
    
    override fun bindPresenter() = ${entityName}Presenter(this)
    
    companion object {
        fun newInstance(): ${entityName}Fragment{
            val fragment = ${entityName}Fragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}
"""

fun someActivity(
        packageName: String,
        basePackageName:String,
        entityName: String,
        layoutName: String,
        projectData: ProjectTemplateData
) = """package $packageName
import ${basePackageName}.mvp.BaseMvpActivity
import android.content.Context
import android.content.Intent
import kotlinx.android.synthetic.main.${layoutName}.*
import ${projectData.applicationPackage}.R;

class ${entityName}Activity : BaseMvpActivity<${entityName}Presenter>(), ${entityName}Contract.View {

    override fun initView() {
    }
    
    override fun initData() {
    }
    
    override fun initListener() {
    }
    
    override fun requestData() {
    }
    
    override fun getLayoutResId() = R.layout.${layoutName}
    
    override fun bindPresenter() = ${entityName}Presenter(this)
    
    companion object {
        fun start(context: Context?) {
            context?.let {
                val intent = Intent(context, ${entityName}Activity::class.java)
                it.startActivity(intent)
            }
        }
    }
}
"""

fun somePresenter(
        packageName: String,
        entityName: String,
        basePackageName:String
) = """package $packageName
import androidx.lifecycle.LifecycleOwner
import ${basePackageName}.mvp.BasePresenter

class ${entityName}Presenter(lifecycleOwner: LifecycleOwner) : BasePresenter<${entityName}Contract.View>(lifecycleOwner), ${entityName}Contract.Presenter{
    
}
"""

fun someContract(
        packageName: String,
        entityName: String,
        basePackageName:String
) = """package $packageName
import ${basePackageName}.mvp.BaseContract

interface ${entityName}Contract{

    interface View : BaseContract.BaseView {
    
    }
    
    interface Presenter: BaseContract.BasePresenter<View>{
    
    }
}
"""

fun someActivityLayout(
        packageName: String,
        entityName: String) =
        """<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context="${packageName}.${entityName}Activity">

</LinearLayout>
"""

fun someFragmentLayout(
        packageName: String,
        entityName: String) =
        """<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context="${packageName}.${entityName}Fragment">

</LinearLayout>
"""
