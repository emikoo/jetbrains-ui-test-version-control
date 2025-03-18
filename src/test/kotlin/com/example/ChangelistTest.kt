package com.example

import com.intellij.driver.sdk.ui.components.common.ideFrame
import com.intellij.driver.sdk.ui.components.common.mainToolbar
import com.intellij.driver.sdk.ui.components.elements.button
import com.intellij.driver.sdk.ui.components.elements.checkBox
import com.intellij.driver.sdk.ui.components.elements.tree
import com.intellij.driver.sdk.waitForIndicators
import com.intellij.ide.starter.driver.engine.runIdeWithDriver
import com.intellij.ide.starter.ide.IdeProductProvider
import com.intellij.ide.starter.junit5.hyphenateWithClass
import com.intellij.ide.starter.models.TestCase
import com.intellij.ide.starter.project.GitHubProject
import com.intellij.ide.starter.runner.CurrentTestMethod
import com.intellij.ide.starter.runner.Starter
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.minutes

class ChangelistTest {

    @Test
    fun openEditorSettingsToRecheckChangelist() {
        val testContext = Starter.newContext(
                CurrentTestMethod.hyphenateWithClass(), TestCase(
                    IdeProductProvider.IU, GitHubProject.fromGithub(
                        branchName = "master", repoRelativeUrl = "android/nowinandroid.git", commitHash = ""
                    )
                )
            ).setLicense(System.getenv("LICENSE_KEY")).prepareProjectCleanImport()

        testContext.runIdeWithDriver().useDriverAndCloseIde {
            ideFrame {
                waitForIndicators(5.minutes)
                mainToolbar.settingsButton.click()
                tree().clickPath("Settings", "Version Control", "Changelists")
                checkBox("//div[@class='JCheckBox' and @text='Create changelists automatically']").click()
                assert(checkBox(xpath = "//div[@class='JCheckBox' and @text='Create changelists automatically']").isSelected())
                button("OK").click()
            }
        }
    }
}