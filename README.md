# Vector Space Model
a Java class utilizing the concept of the vector space model, including TF-IDF scoring and Cosine similarity. This class is able to take a query, retrieve and rank relevant documents

#### VECTORIZING DOCUMENTS

In the vector space model, a document (also a query) is represented with a vector. Each element in the
vector represents the TF-IDF score of the associated term. Though multiple variants of TF-IDF scoring functions
exist, in this project, you must use the simplified version for grading consistency, defined below. Given a term t, a
document d, and the collection of documents D.

> 𝑇𝐹(𝑡, 𝑑)  = 0.5 + ((0.5 ∙ 𝑓(𝑡, 𝑑))/(max{𝑓(𝑡, 𝑑):𝑡 ∈ 𝑑})))

Where f(t,d) denotes the number of occurrences of the term t in the document d

> 𝐼𝐷𝐹(𝑡, 𝐷) = log(N/{𝑑 ∈ 𝐷:𝑡 ∈ 𝑑})

> 𝑇𝐹𝐼𝐷𝐹(𝑡, 𝑑,𝐷) = 𝑇𝐹(𝑡, 𝑑) ∙ 𝐼𝐷𝐹(𝑡,𝐷)


Once documents are represented with vectors (i.e. vectorized), the similarity between two documents could be
computed using the cosine similarity measure. Specifically, the cosine similarity of given vectors 𝐴⃗ and 𝐵⃗⃗ is
defined as:

> cos(𝜃) = (𝐴⃗ ∙ 𝐵⃗⃗ ) / (‖𝐴⃗ ‖ ∙ ‖𝐵⃗⃗ ‖)
