import{_ as e,c as a,o as n,a as s}from"./app-TIUYbXK-.js";const t={},l=s(`<h1 id="remembers" tabindex="-1"><a class="header-anchor" href="#remembers"><span>Remembers</span></a></h1><h2 id="remembermutable" tabindex="-1"><a class="header-anchor" href="#remembermutable"><span>rememberMutable</span></a></h2><p>Remembers mutable state of the value. It is basically <code>remember { mutableStateOf(initialValue) }</code>.</p><div class="language-kotlin line-numbers-mode" data-highlighter="prismjs" data-ext="kt" data-title="kt"><pre class="language-kotlin"><code><span class="line"><span class="token annotation builtin">@Compose</span></span>
<span class="line"><span class="token keyword">fun</span> <span class="token function">Screen</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token punctuation">{</span></span>
<span class="line">    <span class="token keyword">var</span> value <span class="token keyword">by</span> <span class="token function">rememberMutable</span><span class="token punctuation">(</span><span class="token boolean">false</span><span class="token punctuation">)</span></span>
<span class="line"></span>
<span class="line">    <span class="token function">LaunchedEffect</span><span class="token punctuation">(</span>Unit<span class="token punctuation">)</span> <span class="token punctuation">{</span></span>
<span class="line">        loaded <span class="token operator">=</span> <span class="token boolean">true</span></span>
<span class="line">    <span class="token punctuation">}</span></span>
<span class="line">    </span>
<span class="line">    <span class="token keyword">if</span> <span class="token punctuation">(</span>loaded<span class="token punctuation">)</span> <span class="token punctuation">{</span></span>
<span class="line">        <span class="token function">Text</span><span class="token punctuation">(</span><span class="token string-literal singleline"><span class="token string">&quot;Loaded&quot;</span></span><span class="token punctuation">)</span></span>
<span class="line">    <span class="token punctuation">}</span></span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h2 id="remembersaveablemutable" tabindex="-1"><a class="header-anchor" href="#remembersaveablemutable"><span>rememberSaveableMutable</span></a></h2><p>The same as <a href="#remembermutable">rememberMutable</a> but the stored value will survive the activity or process recreation. For example when the screen is rotated in the Android application.</p>`,6),i=[l];function p(r,c){return n(),a("div",null,i)}const m=e(t,[["render",p],["__file","remember.html.vue"]]),u=JSON.parse('{"path":"/compose/remember.html","title":"Remembers","lang":"en-US","frontmatter":{},"headers":[{"level":2,"title":"rememberMutable","slug":"remembermutable","link":"#remembermutable","children":[]},{"level":2,"title":"rememberSaveableMutable","slug":"remembersaveablemutable","link":"#remembersaveablemutable","children":[]}],"git":{"updatedTime":1718816775000,"contributors":[{"name":"Oskar Karpiński","email":"me@medzik.dev","commits":1}]},"filePathRelative":"compose/remember.md"}');export{m as comp,u as data};
