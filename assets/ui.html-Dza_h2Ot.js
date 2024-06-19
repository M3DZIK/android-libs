import{_ as n,c as s,o as a,a as e}from"./app-TIUYbXK-.js";const p={},t=e(`<h1 id="ui" tabindex="-1"><a class="header-anchor" href="#ui"><span>UI</span></a></h1><h2 id="dialog" tabindex="-1"><a class="header-anchor" href="#dialog"><span>Dialog</span></a></h2><h3 id="basedialog" tabindex="-1"><a class="header-anchor" href="#basedialog"><span>BaseDialog</span></a></h3><p>Composable of a basic dialog. <a href="https://www.javadoc.io/doc/dev.medzik.android/compose/latest/compose/dev.medzik.android.compose.ui.dialog/-base-dialog.html" target="_blank" rel="noopener noreferrer">Javadoc documentation</a></p><p>Example:</p><p><img src="https://github.com/M3DZIK/android-utils/assets/87065584/91156b20-7eac-48e5-9909-96c3d64f50fc" alt="BaseDialog preview"></p><div class="language-kotlin line-numbers-mode" data-highlighter="prismjs" data-ext="kt" data-title="kt"><pre class="language-kotlin"><code><span class="line"><span class="token annotation builtin">@Composable</span></span>
<span class="line"><span class="token keyword">fun</span> <span class="token function">BaseDialogExample</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token punctuation">{</span></span>
<span class="line">    <span class="token keyword">val</span> state <span class="token operator">=</span> <span class="token function">rememberDialogState</span><span class="token punctuation">(</span><span class="token punctuation">)</span></span>
<span class="line">    state<span class="token punctuation">.</span><span class="token function">show</span><span class="token punctuation">(</span><span class="token punctuation">)</span></span>
<span class="line"></span>
<span class="line">    <span class="token function">BaseDialog</span><span class="token punctuation">(</span>state<span class="token punctuation">)</span> <span class="token punctuation">{</span></span>
<span class="line">        Column <span class="token punctuation">{</span></span>
<span class="line">            <span class="token function">Text</span><span class="token punctuation">(</span></span>
<span class="line">                text <span class="token operator">=</span> <span class="token string-literal singleline"><span class="token string">&quot;Example Dialog&quot;</span></span><span class="token punctuation">,</span></span>
<span class="line">                fontWeight <span class="token operator">=</span> FontWeight<span class="token punctuation">.</span>Black<span class="token punctuation">,</span></span>
<span class="line">                modifier <span class="token operator">=</span> Modifier</span>
<span class="line">                    <span class="token punctuation">.</span><span class="token function">padding</span><span class="token punctuation">(</span>horizontal <span class="token operator">=</span> <span class="token number">24</span><span class="token punctuation">.</span>dp<span class="token punctuation">)</span></span>
<span class="line">                    <span class="token punctuation">.</span><span class="token function">padding</span><span class="token punctuation">(</span>bottom <span class="token operator">=</span> <span class="token number">8</span><span class="token punctuation">.</span>dp<span class="token punctuation">)</span></span>
<span class="line">            <span class="token punctuation">)</span></span>
<span class="line"></span>
<span class="line">            <span class="token function">Box</span><span class="token punctuation">(</span></span>
<span class="line">                modifier <span class="token operator">=</span> Modifier<span class="token punctuation">.</span><span class="token function">padding</span><span class="token punctuation">(</span>horizontal <span class="token operator">=</span> <span class="token number">24</span><span class="token punctuation">.</span>dp<span class="token punctuation">)</span></span>
<span class="line">            <span class="token punctuation">)</span> <span class="token punctuation">{</span></span>
<span class="line">                <span class="token function">Text</span><span class="token punctuation">(</span><span class="token string-literal singleline"><span class="token string">&quot;Some text&quot;</span></span><span class="token punctuation">)</span></span>
<span class="line">            <span class="token punctuation">}</span></span>
<span class="line">        <span class="token punctuation">}</span></span>
<span class="line">    <span class="token punctuation">}</span></span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h3 id="pickerdialog" tabindex="-1"><a class="header-anchor" href="#pickerdialog"><span>PickerDialog</span></a></h3><p>Dialog with a list of elements to select. <a href="https://www.javadoc.io/doc/dev.medzik.android/compose/latest/compose/dev.medzik.android.compose.ui.dialog/-picker-dialog.html" target="_blank" rel="noopener noreferrer">Javadoc documentation</a></p><p>Example:</p><p><img src="https://github.com/M3DZIK/android-utils/assets/87065584/c35f0c4a-21e2-4a82-b5dd-4f9be93a0990" alt="PickerDialog preview"></p><div class="language-kotlin line-numbers-mode" data-highlighter="prismjs" data-ext="kt" data-title="kt"><pre class="language-kotlin"><code><span class="line"><span class="token annotation builtin">@Composable</span></span>
<span class="line"><span class="token keyword">fun</span> <span class="token function">PickerDialogExample</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token punctuation">{</span></span>
<span class="line">    <span class="token keyword">val</span> state <span class="token operator">=</span> <span class="token function">rememberDialogState</span><span class="token punctuation">(</span><span class="token punctuation">)</span></span>
<span class="line">    state<span class="token punctuation">.</span><span class="token function">show</span><span class="token punctuation">(</span><span class="token punctuation">)</span></span>
<span class="line"></span>
<span class="line">    <span class="token keyword">val</span> items <span class="token operator">=</span> <span class="token function">listOf</span><span class="token punctuation">(</span><span class="token string-literal singleline"><span class="token string">&quot;First&quot;</span></span><span class="token punctuation">,</span> <span class="token string-literal singleline"><span class="token string">&quot;Second&quot;</span></span><span class="token punctuation">,</span> <span class="token string-literal singleline"><span class="token string">&quot;Third&quot;</span></span><span class="token punctuation">)</span></span>
<span class="line"></span>
<span class="line">    <span class="token function">PickerDialog</span><span class="token punctuation">(</span></span>
<span class="line">        state<span class="token punctuation">,</span></span>
<span class="line">        title <span class="token operator">=</span> <span class="token string-literal singleline"><span class="token string">&quot;Example Picker Dialog&quot;</span></span><span class="token punctuation">,</span></span>
<span class="line">        items <span class="token operator">=</span> items<span class="token punctuation">,</span></span>
<span class="line">        onSelected <span class="token operator">=</span> <span class="token punctuation">{</span><span class="token punctuation">}</span></span>
<span class="line">    <span class="token punctuation">)</span> <span class="token punctuation">{</span></span>
<span class="line">        <span class="token function">Text</span><span class="token punctuation">(</span></span>
<span class="line">            text <span class="token operator">=</span> it<span class="token punctuation">,</span></span>
<span class="line">            modifier <span class="token operator">=</span> Modifier</span>
<span class="line">                <span class="token punctuation">.</span><span class="token function">padding</span><span class="token punctuation">(</span>vertical <span class="token operator">=</span> <span class="token number">12</span><span class="token punctuation">.</span>dp<span class="token punctuation">)</span></span>
<span class="line">                <span class="token punctuation">.</span><span class="token function">fillMaxWidth</span><span class="token punctuation">(</span><span class="token punctuation">)</span></span>
<span class="line">        <span class="token punctuation">)</span></span>
<span class="line">    <span class="token punctuation">}</span></span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h2 id="preference" tabindex="-1"><a class="header-anchor" href="#preference"><span>Preference</span></a></h2><h3 id="basicpreference" tabindex="-1"><a class="header-anchor" href="#basicpreference"><span>BasicPreference</span></a></h3><p>Basic preference entry. <a href="https://www.javadoc.io/doc/dev.medzik.android/compose/latest/compose/dev.medzik.android.compose.ui.preference/-basic-preference.html" target="_blank" rel="noopener noreferrer">Javadoc documentation</a></p><p>Example:</p><p><img src="https://github.com/M3DZIK/android-utils/assets/87065584/031c99e5-e698-41ae-86bd-a20448b6f1fe" alt="BasicPreference preview"></p><div class="language-kotlin line-numbers-mode" data-highlighter="prismjs" data-ext="kt" data-title="kt"><pre class="language-kotlin"><code><span class="line"><span class="token annotation builtin">@Composable</span></span>
<span class="line"><span class="token keyword">fun</span> <span class="token function">BasicPreferenceExample</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token punctuation">{</span></span>
<span class="line">    <span class="token function">BasicPreference</span><span class="token punctuation">(</span></span>
<span class="line">        title <span class="token operator">=</span> <span class="token string-literal singleline"><span class="token string">&quot;Settings&quot;</span></span><span class="token punctuation">,</span></span>
<span class="line">        subtitle <span class="token operator">=</span> <span class="token string-literal singleline"><span class="token string">&quot;Switch to settings screen&quot;</span></span><span class="token punctuation">,</span></span>
<span class="line">        onClick <span class="token operator">=</span> <span class="token punctuation">{</span> <span class="token function">println</span><span class="token punctuation">(</span><span class="token string-literal singleline"><span class="token string">&quot;Clicked!&quot;</span></span><span class="token punctuation">)</span> <span class="token punctuation">}</span><span class="token punctuation">,</span></span>
<span class="line">        leading <span class="token operator">=</span> <span class="token punctuation">{</span></span>
<span class="line">            <span class="token function">IconBox</span><span class="token punctuation">(</span>Icons<span class="token punctuation">.</span>Default<span class="token punctuation">.</span>Settings<span class="token punctuation">)</span></span>
<span class="line">        <span class="token punctuation">}</span><span class="token punctuation">,</span></span>
<span class="line">        trailing <span class="token operator">=</span> <span class="token punctuation">{</span></span>
<span class="line">            <span class="token function">IconBox</span><span class="token punctuation">(</span>Icons<span class="token punctuation">.</span>Default<span class="token punctuation">.</span>ArrowOutward<span class="token punctuation">)</span></span>
<span class="line">        <span class="token punctuation">}</span></span>
<span class="line">    <span class="token punctuation">)</span></span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h3 id="preferencegrouptitle" tabindex="-1"><a class="header-anchor" href="#preferencegrouptitle"><span>PreferenceGroupTitle</span></a></h3><p>Title component for a group of preferences. <a href="https://www.javadoc.io/doc/dev.medzik.android/compose/latest/compose/dev.medzik.android.compose.ui.preference/-preference-group-title.html" target="_blank" rel="noopener noreferrer">Javadoc documentation</a></p><p>Example:</p><p><img src="https://github.com/M3DZIK/android-utils/assets/87065584/68d7e152-1058-41b3-b275-83e74cc91391" alt="PreferenceGroupTitle preview"></p><div class="language-kotlin line-numbers-mode" data-highlighter="prismjs" data-ext="kt" data-title="kt"><pre class="language-kotlin"><code><span class="line"><span class="token annotation builtin">@Composable</span></span>
<span class="line"><span class="token keyword">fun</span> <span class="token function">PreferenceGroupTitleExample</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token punctuation">{</span></span>
<span class="line">    Column <span class="token punctuation">{</span></span>
<span class="line">        <span class="token function">PreferenceGroupTitle</span><span class="token punctuation">(</span><span class="token string-literal singleline"><span class="token string">&quot;Group title&quot;</span></span><span class="token punctuation">)</span></span>
<span class="line"></span>
<span class="line">        <span class="token function">SwitcherPreference</span><span class="token punctuation">(</span></span>
<span class="line">            title <span class="token operator">=</span> <span class="token string-literal singleline"><span class="token string">&quot;Switcher Preference&quot;</span></span><span class="token punctuation">,</span></span>
<span class="line">            checked <span class="token operator">=</span> <span class="token boolean">false</span><span class="token punctuation">,</span></span>
<span class="line">            onCheckedChange <span class="token operator">=</span> <span class="token punctuation">{</span><span class="token punctuation">}</span></span>
<span class="line">        <span class="token punctuation">)</span></span>
<span class="line">    <span class="token punctuation">}</span></span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h3 id="propertypreference" tabindex="-1"><a class="header-anchor" href="#propertypreference"><span>PropertyPreference</span></a></h3><p>Preference entry with a property. <a href="https://www.javadoc.io/doc/dev.medzik.android/compose/latest/compose/dev.medzik.android.compose.ui.preference/-property-preference.html" target="_blank" rel="noopener noreferrer">Javadoc documentation</a></p><p>Example:</p><p><img src="https://github.com/M3DZIK/android-utils/assets/87065584/401e3a8f-8db9-452b-af81-4b23220723dc" alt="PropertyPreference preview"></p><div class="language-kotlin line-numbers-mode" data-highlighter="prismjs" data-ext="kt" data-title="kt"><pre class="language-kotlin"><code><span class="line"><span class="token annotation builtin">@Composable</span></span>
<span class="line"><span class="token keyword">fun</span> <span class="token function">PropertyPreferenceExample</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token punctuation">{</span></span>
<span class="line">    <span class="token keyword">val</span> propertyItems <span class="token operator">=</span> <span class="token function">listOf</span><span class="token punctuation">(</span><span class="token string-literal singleline"><span class="token string">&quot;First&quot;</span></span><span class="token punctuation">,</span> <span class="token string-literal singleline"><span class="token string">&quot;Second&quot;</span></span><span class="token punctuation">,</span> <span class="token string-literal singleline"><span class="token string">&quot;Third&quot;</span></span><span class="token punctuation">)</span></span>
<span class="line">    <span class="token keyword">val</span> currentItem <span class="token keyword">by</span> <span class="token function">rememberMutable</span><span class="token punctuation">(</span>propertyItems<span class="token punctuation">[</span><span class="token number">0</span><span class="token punctuation">]</span><span class="token punctuation">)</span></span>
<span class="line"></span>
<span class="line">    <span class="token function">PropertyPreference</span><span class="token punctuation">(</span></span>
<span class="line">        title <span class="token operator">=</span> <span class="token string-literal singleline"><span class="token string">&quot;Property title&quot;</span></span><span class="token punctuation">,</span></span>
<span class="line">        subtitle <span class="token operator">=</span> <span class="token string-literal singleline"><span class="token string">&quot;Property subtitle&quot;</span></span><span class="token punctuation">,</span></span>
<span class="line">        leading <span class="token operator">=</span> <span class="token punctuation">{</span></span>
<span class="line">            <span class="token function">IconBox</span><span class="token punctuation">(</span>Icons<span class="token punctuation">.</span>Default<span class="token punctuation">.</span>Lock<span class="token punctuation">)</span></span>
<span class="line">        <span class="token punctuation">}</span><span class="token punctuation">,</span></span>
<span class="line">        currentValue <span class="token operator">=</span> currentItem<span class="token punctuation">,</span></span>
<span class="line">        onClick <span class="token operator">=</span> <span class="token punctuation">{</span> state<span class="token punctuation">.</span><span class="token function">show</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token punctuation">}</span></span>
<span class="line">    <span class="token punctuation">)</span></span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h3 id="switcherpreference" tabindex="-1"><a class="header-anchor" href="#switcherpreference"><span>SwitcherPreference</span></a></h3><p>Preference entry with a switcher. <a href="https://www.javadoc.io/doc/dev.medzik.android/compose/latest/compose/dev.medzik.android.compose.ui.preference/-switcher-preference.html" target="_blank" rel="noopener noreferrer">Javadoc documentation</a></p><p>Example:</p><p><img src="https://github.com/M3DZIK/android-utils/assets/87065584/b8b1f428-15d2-499a-bdda-138e01900fb2" alt="SwitcherPreference preview"></p><div class="language-kotlin line-numbers-mode" data-highlighter="prismjs" data-ext="kt" data-title="kt"><pre class="language-kotlin"><code><span class="line"><span class="token annotation builtin">@Composable</span></span>
<span class="line"><span class="token keyword">fun</span> <span class="token function">SwitcherPreferenceExample</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token punctuation">{</span></span>
<span class="line">    <span class="token keyword">val</span> checked <span class="token operator">=</span> <span class="token function">rememberMutable</span><span class="token punctuation">(</span><span class="token boolean">false</span><span class="token punctuation">)</span></span>
<span class="line"></span>
<span class="line">    <span class="token function">SwitcherPreference</span><span class="token punctuation">(</span></span>
<span class="line">        title <span class="token operator">=</span> <span class="token string-literal singleline"><span class="token string">&quot;Switcher Preference&quot;</span></span><span class="token punctuation">,</span></span>
<span class="line">        checked <span class="token operator">=</span> checked</span>
<span class="line">    <span class="token punctuation">)</span></span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div>`,33),i=[t];function l(c,o){return a(),s("div",null,i)}const u=n(p,[["render",l],["__file","ui.html.vue"]]),d=JSON.parse('{"path":"/compose/ui.html","title":"UI","lang":"en-US","frontmatter":{},"headers":[{"level":2,"title":"Dialog","slug":"dialog","link":"#dialog","children":[{"level":3,"title":"BaseDialog","slug":"basedialog","link":"#basedialog","children":[]},{"level":3,"title":"PickerDialog","slug":"pickerdialog","link":"#pickerdialog","children":[]}]},{"level":2,"title":"Preference","slug":"preference","link":"#preference","children":[{"level":3,"title":"BasicPreference","slug":"basicpreference","link":"#basicpreference","children":[]},{"level":3,"title":"PreferenceGroupTitle","slug":"preferencegrouptitle","link":"#preferencegrouptitle","children":[]},{"level":3,"title":"PropertyPreference","slug":"propertypreference","link":"#propertypreference","children":[]},{"level":3,"title":"SwitcherPreference","slug":"switcherpreference","link":"#switcherpreference","children":[]}]}],"git":{"updatedTime":1718816775000,"contributors":[{"name":"Oskar Karpiński","email":"me@medzik.dev","commits":1}]},"filePathRelative":"compose/ui.md"}');export{u as comp,d as data};
