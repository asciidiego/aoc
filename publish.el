(package-initialize)

(require 'ox-md)

(org-babel-do-load-languages
    'org-babel-load-languages
    '((python . t)
      ;;(dot . t)
      (shell . t)))

;; Define the publishing project
(setq org-publish-project-alist
      (list
       (list "org-files"
             :recursive t
             :base-directory "./"
             :publishing-directory "./"
             :publishing-function 'org-md-publish-to-md)))

;; Generate the output
(org-publish-all t)
