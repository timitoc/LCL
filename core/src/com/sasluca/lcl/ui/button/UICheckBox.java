package com.sasluca.lcl.ui.button;

import com.sasluca.lcl.abstractions.ISizeable;
import com.sasluca.lcl.animation.LCLTween;
import com.sasluca.lcl.ui.view.UIView;

/*
 * Copyright 2016 Sas Luca
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public abstract class UICheckBox extends UIView<UICheckBox> implements ISizeable<UICheckBox>
{
    static { LCLTween.addClass(UICheckBox.class); }

    private boolean m_Checked;

    public boolean isChecked() { return m_Checked; }

    protected abstract void onCheck();
    protected abstract void onUncheck();

    public UICheckBox check()
    {
        m_Checked = true;

        onCheck();

        return this;
    }

    public UICheckBox uncheck()
    {
        m_Checked = false;

        onUncheck();

        return this;
    }

    @Override public void onFocus()
    {
        if(m_Checked) uncheck(); else check();
    }
}