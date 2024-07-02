import{_ as n,c as s,o as a,a as e}from"./app-Cffz9ejT.js";const t={},p=e(`<h1 id="keystore" tabindex="-1"><a class="header-anchor" href="#keystore"><span>KeyStore</span></a></h1><p><a href="https://www.javadoc.io/doc/dev.medzik.android/crypto/latest/crypto/dev.medzik.android.crypto/-key-store/index.html" target="_blank" rel="noopener noreferrer">Javadoc documentation</a></p><h2 id="key-alias" tabindex="-1"><a class="header-anchor" href="#key-alias"><span>Key alias</span></a></h2><p>To create a keystore key alias you need to create a class that extends <code>KeyStoreAlias</code>.</p><div class="language-kotlin line-numbers-mode" data-highlighter="prismjs" data-ext="kt" data-title="kt"><pre class="language-kotlin"><code><span class="line"><span class="token keyword">enum</span> <span class="token keyword">class</span> Key <span class="token operator">:</span> KeyStoreAlias <span class="token punctuation">{</span></span>
<span class="line">    TestKey</span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>or</p><div class="language-kotlin line-numbers-mode" data-highlighter="prismjs" data-ext="kt" data-title="kt"><pre class="language-kotlin"><code><span class="line"><span class="token keyword">object</span> TestKeyAlias <span class="token operator">:</span> KeyStoreAlias <span class="token punctuation">{</span></span>
<span class="line">    <span class="token keyword">override</span> <span class="token keyword">val</span> name <span class="token operator">=</span> <span class="token string-literal singleline"><span class="token string">&quot;test_key&quot;</span></span></span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h2 id="encryption-and-decryption" tabindex="-1"><a class="header-anchor" href="#encryption-and-decryption"><span>Encryption and Decryption</span></a></h2><p>Example code of encryption and decryption text using Android KeyStore:</p><div class="language-kotlin line-numbers-mode" data-highlighter="prismjs" data-ext="kt" data-title="kt"><pre class="language-kotlin"><code><span class="line"><span class="token keyword">import</span> dev<span class="token punctuation">.</span>medzik<span class="token punctuation">.</span>android<span class="token punctuation">.</span>crypto<span class="token punctuation">.</span>KeyStore</span>
<span class="line"><span class="token keyword">import</span> dev<span class="token punctuation">.</span>medzik<span class="token punctuation">.</span>libcrypto<span class="token punctuation">.</span>Hex</span>
<span class="line"></span>
<span class="line"><span class="token keyword">val</span> alias <span class="token operator">=</span> Key<span class="token punctuation">.</span>TestKey <span class="token comment">// or TestKeyAlias</span></span>
<span class="line"><span class="token keyword">val</span> inputText <span class="token operator">=</span> <span class="token string-literal singleline"><span class="token string">&quot;Hello World!&quot;</span></span></span>
<span class="line"></span>
<span class="line"><span class="token comment">// initialize cipher for encryption</span></span>
<span class="line"><span class="token keyword">val</span> initializedCipherForEncryption <span class="token operator">=</span> KeyStore<span class="token punctuation">.</span><span class="token function">initForEncryption</span><span class="token punctuation">(</span>alias<span class="token punctuation">,</span> deviceAuthentication <span class="token operator">=</span> <span class="token boolean">false</span><span class="token punctuation">)</span></span>
<span class="line"><span class="token comment">// encrypt the input</span></span>
<span class="line"><span class="token keyword">val</span> encryptedText <span class="token operator">=</span> KeyStore<span class="token punctuation">.</span><span class="token function">encrypt</span><span class="token punctuation">(</span>initializedCipherForEncryption<span class="token punctuation">,</span> clearText<span class="token punctuation">.</span><span class="token function">toByteArray</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">)</span></span>
<span class="line"></span>
<span class="line"><span class="token comment">// initialize cipher for decryption</span></span>
<span class="line"><span class="token keyword">val</span> initializedCipherForDecryption <span class="token operator">=</span> KeyStore<span class="token punctuation">.</span><span class="token function">initForDecryption</span><span class="token punctuation">(</span></span>
<span class="line">    alias<span class="token punctuation">,</span></span>
<span class="line">    Hex<span class="token punctuation">.</span><span class="token function">decode</span><span class="token punctuation">(</span>encryptedData<span class="token punctuation">.</span>initializationVector<span class="token punctuation">)</span><span class="token punctuation">,</span></span>
<span class="line">    deviceAuthentication <span class="token operator">=</span> <span class="token boolean">false</span></span>
<span class="line"><span class="token punctuation">)</span></span>
<span class="line"><span class="token comment">// decrypt the cipher text</span></span>
<span class="line"><span class="token keyword">val</span> decryptedBytes <span class="token operator">=</span> KeyStore<span class="token punctuation">.</span><span class="token function">decrypt</span><span class="token punctuation">(</span>initializedCipherForDecryption<span class="token punctuation">,</span> encryptedData<span class="token punctuation">.</span>cipherText<span class="token punctuation">)</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div>`,10),i=[p];function l(o,c){return a(),s("div",null,i)}const d=n(t,[["render",l],["__file","keystore.html.vue"]]),u=JSON.parse('{"path":"/crypto/keystore.html","title":"KeyStore","lang":"en-US","frontmatter":{},"headers":[{"level":2,"title":"Key alias","slug":"key-alias","link":"#key-alias","children":[]},{"level":2,"title":"Encryption and Decryption","slug":"encryption-and-decryption","link":"#encryption-and-decryption","children":[]}],"git":{"updatedTime":1718828825000,"contributors":[{"name":"Oskar Karpiński","email":"me@medzik.dev","commits":1}]},"filePathRelative":"crypto/keystore.md"}');export{d as comp,u as data};