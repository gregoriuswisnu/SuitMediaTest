# SuitMediaTest
Mobile App Development

Buat Aplikasi di iPhone/Android (sesuai pilihan) dengan spesifikasi :
terdiri 4 screen :
1. Home; berisi masukan nama
2. pilih event/guest; berisi label/textview nama sesuai masukan, 2 button :
a. pilih event, jika dipilih menuju ke screen 3.
b. pilih guest, jika dipilih menuju ke screen 4.
3. list/table view events (data dummy minimal 4, tapi harus mengimplementasikan model event),
dengan atribut image, nama, dan tanggal. Jika dipilih kembali ke screen 2, button a diisi nama event yang
dipilih (tidak boleh buat screen baru).
4. grid/collectionview guests (data dari http://www.mocky.io/v2/596dec7f0f000023032b8017
), dengan atribut nama, birthdate. Image dummy. Jika dipilih kembali ke screen 2, button b diisi nama guest
yang dipilih (tidak boleh buat screen baru). Dan muncul toast/alert dialog/alert view dengan ketentuan :
Jika tanggal lahir kelipatan 2 keluarnya blackberry
Jika tanggal lahir kelipatan 3 keluarnya android
Jika tanggal lahir kelipatan (2 dan 3) keluarnya iOS
Selain itu keluarnya feature phone
keterangan liat gambar dibawah. Kirimkan github/bitbucket url dan screenshoot. (Untuk Android kirimkan
.apk)


1. Question 1
From Previous screening test app -> home view -> there is input text to fill name.
Write a method to determine is palindrome or not :
e.g isPalindrome(“kasur rusak”) -> true
e.g isPalindrome(“step on no pets”) -> true
e.g isPalindrome(“put it up”) -> true
e.g isPalindrome(“suitmedia”) -> false
Show as dialog with message “isPalindrome” if it’s palindrome and message “not palindrome” if it’s not
palindrome.
2. Question 2
From Previous screen test app -> guest grid/collection view
Add a method to determine the month is prime or not.
3. Question 3
See attachment-> visual design.zip.
Enhance UI Home view at screening test app to be like screen1.png
Enhance UI Event List view to be like screenevent.png.
Add pull to refresh at Guest collection view + cache data.
If we click “plus button on top right”, event list view change to be map view (see mapview.png), please use
fragment not activity.
Set lat & long as dummy data add on event model. And horizontal event list on top swipeable, If we swipe,
pin point will move to event selected.
Find assets on attachment to help develop that views.
Send your repo link and screen shot.
