(package-initialize)

(require 'ox-md)

(org-babel-do-load-languages
    'org-babel-load-languages
    '((python . t)
      (shell . t)))

;; Define the publishing project
(setq org-publish-project-alist
      (list
       (list "org-files"
             :recursive t
             :base-directory "./"
             :publishing-directory "./"
             :publishing-function 'org-md-publish-to-md)))

(defun my-org-confirm-babel-evaluate (lang body)
  "Do not ask for confirmation based on LANG. Argument BODY is unused."
  (not (string= lang "shell")))
(setq org-confirm-babel-evaluate #'my-org-confirm-babel-evaluate)

;; Generate the output
(org-publish-all t)
