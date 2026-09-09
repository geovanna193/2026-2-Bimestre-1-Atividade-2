(ns atv3)

(defn worker
  [numero tempo_trabalho]
  (future
    (println (str "trabalhador" numero "comeÃ§ou"))
    (Thread/sleep (* tempo_trabalho  1000))
    (println (str "trabalhador" numero "terminou (levou" tempo_trabalho "s)"))))



(defn main []
  (println "Iniciando 5 trabalhadores...")
  (let [inicio (System/currentTimeMillis)
        futuros (mapv (fn [i] (future (worker i 2)))
                      (range 5))]
    (doseq [f futuros] @f)
    (let [fim ( System/currentTimeMillis)
          tempo-total (/ (- fim inicio) 1000.0)]
      (println "\nTodos os trabalhadores terminaram!")
      (println (format "Tempo total: %.2fs" tempo-total))
      (println "(se fosse sequencial, levaria 0s)")))
  (shutdown-agents))

(main)