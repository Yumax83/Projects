package com.example.filemanagerprojectapp.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.Settings;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.filemanagerprojectapp.FileAdapter;
import com.example.filemanagerprojectapp.FileOpener;
import com.example.filemanagerprojectapp.OnFileSelectedListener;
import com.example.filemanagerprojectapp.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardFragment extends Fragment implements OnFileSelectedListener {
    View view;
    File storage;

    private FileAdapter fileAdapter;
    private RecyclerView recyclerView;

    String data1;

    String[] items = {"Details", "Rename", "Delete"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_card, container, false);

        ImageView imgBackCard = view.findViewById(R.id.img_back_card);
        TextView tvPathHolderCard = view.findViewById(R.id.tv_path_holder_card);

//и третий -
//// метод поиска корня карты.  его просто вызываем когда получаем доступ к карте
///*    // ищем корень sd карты
//    //Цель метода  Из стандартного пути вроде: /storage/XXXX-XXXX/Android/data/com.example.app/files/
//    //получить: /storage/XXXX-XXXX То есть корневую директорию SD-карты.
//    public File getSdCardRoot() {
//        // Метод возвращает массив путей:
//        //dirs[0] — внутренняя память: /storage/emulated/0/Android/data/...
//        //dirs[1] — SD-карта (если есть): /storage/XXXX-XXXX/Android/data/...
//        File[] dirs = ContextCompat.getExternalFilesDirs(getContext(), null);
//        //хотим использовать второй путь — dirs[1].
//        if (dirs != null && dirs.length > 1 && dirs[1] != null) {
//            File sdPath = dirs[1]; //Сохраняем путь к SD-карте в переменную sdPath. На этом этапе он выглядит примерно так: /storage/XXXX-XXXX/Android/data/com.example.app/files/
//            File parent;
//            // Поднимаемся вверх, пока не найдём родителя с именем "storage"
//            //цикл, который "шагает" вверх по дереву папок. Что мы делаем:
//            //sdPath.getParentFile() — получаем родительскую папку.
//            //Проверяем: имя папки не равно "storage" (всё ещё на SD-карте).
//            while ((parent = sdPath.getParentFile()) != null && !parent.getName().equalsIgnoreCase("storage")) {
//                sdPath = parent; //Если не дошли до /storage, то делаем шаг назад: sdPath = parent.
//            }
//            if (parent != null && parent.getName().equalsIgnoreCase("storage")) {
//                return sdPath; //Когда мы дошли до /storage/XXXX-XXXX, возвращаем этот путь.
//                //Пример как работает:
//                //Шаг 1: /storage/XXXX-XXXX/Android/data/... → родитель = /storage/XXXX-XXXX/Android/data/
//                //Шаг 2: → /storage/XXXX-XXXX/Android/
//                //Шаг 3: → /storage/XXXX-XXXX/
//                //Шаг 4: родитель = /storage/ → имя storage — стоп! Цикл завершён.
//            }
//        }
//        return null;

        File[] externalDirs = ContextCompat.getExternalFilesDirs(getContext(), null);
        // Метод getExternalFilesDirs() Получает массив путей, где приложение может хранить файлы:
        // externalDirs[0] — это внутренняя память (например:
        // externalDirs[1] — это SD-карта, если она вставлена

        if (externalDirs.length > 1 && externalDirs[1] != null) {
            // Проверка: В массиве есть хотя бы 2 пути (length > 1) и Второй путь (SD-карта) реально существует (не null).
            // если проще то строка читается так "Если в массиве есть второй путь, и он реально указывает на SD-карту — работаем с ним!"           // Внешняя SD-карта доступна. Действие:           storage = new File(externalDirs[1].getAbsolutePath()); // Запоминаешь путь к папке на SD-карте           cardTvPathFolder.setText("SD-карта: " + storage.getAbsolutePath());// Показываешь путь на экране           runtimePermission(); // вызов метода запроса разрешений       } else {           // Если SD-карты нет — используем внутреннюю           storage = new File(Environment.getExternalStorageDirectory().getAbsolutePath()); //Используем стандартный путь к внутренней памяти           cardTvPathFolder.setText("Встроенная память: " + storage.getAbsolutePath()); //Отображаем, что работаем с внутренней           runtimePermission(); // вызов метода запроса разрешений

            // получаем доступ к внешней карте памяти
            System.out.println("sss 1: " + storage);

            //getAbsolutePath().split("/Android")[0]);
           // getAbsolutePath().substring(0,19);

            storage = new File(externalDirs[1].getAbsolutePath()); // Запоминаешь путь к папке на SD-карте
            System.out.println("sss 2: " + storage);
            //tvPathHolderCard.setText("Внешняя SD-карта (список файлов и папок):");// Показываешь путь на экране

            tvPathHolderCard.setText("SD-карта: " + storage.getAbsolutePath());// Показываешь путь на экране
            // вызов метода запроса разрешений
           // System.out.println("ddd 1: " + data1);
            if (getArguments() != null) {
                data1 = getArguments().getString("path1");
                storage = new File(data1);
              //  System.out.println("ddd 2: " + data1);

            }
            runtimePermission();

        } else {
            // Если SD-карты нет — используем внутреннюю
            storage = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            //Используем стандартный путь к внутренней памяти
//            if (getArguments() != null) {
//                data = getArguments().getString("path");
//                storage1 = new File(data);
//            }
            tvPathHolderCard.setText("Встроенная память: " + storage.getAbsolutePath());
            //Отображаем, что работаем с внутренней
            runtimePermission(); // вызов метода запроса разрешений
        }


        return view;
    }

    private void runtimePermission() {
        // Разрешение до Android 10 (API 29)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                displayFiles();
            }
        }
        // Разрешения для Android 11 (API 30) и выше
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                try {
                    Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    intent.setData(uri);
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse(String.format("package:%s", getActivity().getPackageName())));
                    getActivity().startActivityIfNeeded(intent, 101);
                } catch (Exception e) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    getActivity().startActivityIfNeeded(intent, 101);
                }
            }
            if (Environment.isExternalStorageManager()) {
                displayFiles();
            }
        }
    }

    public ArrayList<File> findFiles(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        for (File singleFile : files) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                arrayList.add(singleFile);
            }
        }

        for (File singleFile : files) {
            if (singleFile.getName().toLowerCase().endsWith(".jpeg") ||
                    singleFile.getName().toLowerCase().endsWith(".jpg") ||
                    singleFile.getName().toLowerCase().endsWith(".png") ||
                    singleFile.getName().toLowerCase().endsWith(".mp3") ||
                    singleFile.getName().toLowerCase().endsWith(".wav") ||
                    singleFile.getName().toLowerCase().endsWith(".mp4") ||
                    singleFile.getName().toLowerCase().endsWith(".pdf") ||
                    singleFile.getName().toLowerCase().endsWith(".doc") ||
                    singleFile.getName().toLowerCase().endsWith(".apk")) {
                arrayList.add(singleFile);
            }
        }
        return arrayList;
    }

    private void displayFiles() {
        recyclerView = view.findViewById(R.id.recycler_card);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        List<File> fileList1 = new ArrayList<>();
        fileList1.addAll(findFiles(storage));

        fileAdapter = new FileAdapter(getContext(), fileList1, this);
        recyclerView.setAdapter(fileAdapter);
    }

    @Override
    public void onFileClicked(File file) {
        if (file.isDirectory()) {
            Bundle bundle = new Bundle();
          //  System.out.println("bbb: " + bundle1);
            bundle.putString("path1", file.getAbsolutePath());
         //  System.out.println("bbb: " + bundle1);

            CardFragment cardFragment = new CardFragment();
            cardFragment.setArguments(bundle);

            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, cardFragment).addToBackStack(null).commit();

        } else {
            FileOpener.openFile(getContext(), file);
        }
    }


    @Override
    public void onFileLongClicked(File file) {
        final Dialog optionDialog = new Dialog(getContext());
        optionDialog.setContentView(R.layout.option_dialog);
        optionDialog.setTitle("Select Options.");
        ListView options = optionDialog.findViewById(R.id.list);

        CustomAdapter1 customAdapter1 = new CustomAdapter1();
        options.setAdapter(customAdapter1);

        optionDialog.show();

        options.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                switch (selectedItem) {
                    case "Details":
                        AlertDialog.Builder detailDialog = new AlertDialog.Builder(getContext());
                        detailDialog.setTitle("Details");
                        final TextView details = new TextView(getContext());
                        detailDialog.setView(details);
                        Date lastModified = new Date(file.lastModified());
                        SimpleDateFormat formatted = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        String formattedDate = formatted.format(lastModified);

//                        details.setText(String.format(
//                                "File Name: " + file.getName() + "\n" + "Size: "
//                                        + Formatter.formatFileSize(getContext(), file.length()) + "\n" + "Path: " + file.getAbsolutePath()
//                                        + "\n" + "Last Modified: " + formattedDate));

                        details.setText(String.format("File Name: %s\nSize: %s\nPath: %s\nLast Modified: %s",
                                file.getName(), Formatter.formatShortFileSize(getContext(), file.length()), file.getAbsolutePath(), formattedDate));

                        details.setPadding(70, 10, 10, 10);

                        detailDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // optionDialog.cancel(); //закрывает два окна
                                optionDialog.closeOptionsMenu(); //закрывает одно окно
                            }
                        });

                        AlertDialog alertDialogDetails = detailDialog.create();
                        alertDialogDetails.show();
                        break;

                }
            }
        });

    }
    class CustomAdapter1 extends BaseAdapter {

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView = getLayoutInflater().inflate(R.layout.option_layout, null);
            TextView txtOptions = myView.findViewById(R.id.txt_option);
            ImageView imgOptions = myView.findViewById(R.id.img_option);

            txtOptions.setText(items[position]);

            if (items[position].equals("Details")) {
                imgOptions.setImageResource(R.drawable.baseline_info);
            } else if (items[position].equals("Rename")) {
                imgOptions.setImageResource(R.drawable.baseline_edit);
            } else if (items[position].equals("Delete")) {
                imgOptions.setImageResource(R.drawable.baseline_delete);
            }
            return myView;
        }
    }
}